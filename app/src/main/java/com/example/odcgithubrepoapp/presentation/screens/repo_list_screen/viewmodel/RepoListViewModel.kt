package com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.odcgithubrepoapp.domain.model.CustomRemoteExceptionDomainModel
import com.example.odcgithubrepoapp.presentation.mapper.toCustomExceptionRemoteUiModel
import com.example.odcgithubrepoapp.presentation.mapper.toGithubReposUiModel
import com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.model.RepoListUiState
import com.mahmoud.domain.usecase.CheckOnBoardingStateUseCase
import com.mahmoud.domain.usecase.FetchGithubReposListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoListViewModel @Inject constructor(
    private val githubReposListUseCase: FetchGithubReposListUseCase,
    private val checkOnBoardingState: CheckOnBoardingStateUseCase

) : ViewModel() {
    private val _repoListStateFlow =
        MutableStateFlow<RepoListUiState>(RepoListUiState(isLoading = true))
    val repoListStateFlow: StateFlow<RepoListUiState> = _repoListStateFlow.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _repoListStateFlow.value = RepoListUiState(
            isLoading = false,
            isError = true,
            customRemoteExceptionUiModel = (throwable as CustomRemoteExceptionDomainModel).toCustomExceptionRemoteUiModel()
        )
    }

    init {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            checkOnBoardingState.readOnBoardingState().collect { completed ->
                Log.d("Is First Time: ", "Yes, first time")
                if (completed) {
//                    refreshDataAndGetIt()
//                    checkOnBoardingState.saveOnBoardingState(true)

                    try {
                        //Get data from remote and save it to local
                        githubReposListUseCase.refreshRepoList()
                        //Get data from local
                        requestGithubRepoList()
                        Log.d("Is First Time: ", "No, not first time")
                    } catch (e: Exception) {
                        requestGithubRepoList()
                    }
                } else {
                    refreshDataAndGetIt()
                }
            }


        }
    }

    fun refreshDataAndGetIt() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            try {
                githubReposListUseCase.refreshRepoList()
                requestGithubRepoList()
            } catch (e: Exception) {
                Log.d("Fetching data: ", "Throw exception!")
                _repoListStateFlow.value = RepoListUiState(
                    isLoading = false,
                    isError = true,
                    customRemoteExceptionUiModel = (e as CustomRemoteExceptionDomainModel).toCustomExceptionRemoteUiModel()
                )
            }
        }
    }

    private fun requestGithubRepoList() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            try {
                val repoList = githubReposListUseCase.fetchRepoList()
                _repoListStateFlow.value = RepoListUiState(
                    isLoading = false,
                    repoList = repoList.map { it.toGithubReposUiModel() }
                )
            } catch (e: Exception) {
                Log.d("Fetching data: ", "Throw exception & display error screen")
                _repoListStateFlow.value = RepoListUiState(
                    isLoading = false,
                    isError = true,
                    customRemoteExceptionUiModel = (e as CustomRemoteExceptionDomainModel).toCustomExceptionRemoteUiModel()
                )
            }
        }
    }
}