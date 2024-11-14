package com.example.odcgithubrepoapp.presentation.screens.repo_search_screen.viewmodel

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.odcgithubrepoapp.domain.model.CustomRemoteExceptionDomainModel
import com.example.odcgithubrepoapp.presentation.mapper.toCustomExceptionRemoteUiModel
import com.example.odcgithubrepoapp.presentation.mapper.toGithubReposUiModel
import com.example.odcgithubrepoapp.presentation.screens.repo_search_screen.model.SearchRepoUiState
import com.mahmoud.domain.usecase.SearchReposUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoSearchViewModel @Inject constructor(
    private val searchReposUseCase: SearchReposUseCase
) : ViewModel() {
    private val _searchRepoStateFlow =
        MutableStateFlow<SearchRepoUiState>(SearchRepoUiState(isLoading = false))
    var searchRepoStateFlow: StateFlow<SearchRepoUiState> = _searchRepoStateFlow.asStateFlow()


    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _searchRepoStateFlow.value = SearchRepoUiState(
            isLoading = false,
            isError = true,
            customRemoteExceptionUiModel = (throwable as CustomRemoteExceptionDomainModel).toCustomExceptionRemoteUiModel()
        )
    }

    fun startLoading(){
        _searchRepoStateFlow.value = SearchRepoUiState(
            isLoading = true,
            repoList = emptyFlow()

        )
    }
    fun stopLoading(){
        _searchRepoStateFlow.value = SearchRepoUiState(
            isLoading = false,
            repoList = emptyFlow()

        )
    }
    fun clearResultList(){
        _searchRepoStateFlow.value = SearchRepoUiState(
            repoList = emptyFlow()
        )
    }

    fun searchRepos(query: String) {
        startLoading()
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            try {
                val searchResult = searchReposUseCase(query).cachedIn(viewModelScope)
                _searchRepoStateFlow.value = SearchRepoUiState(
                    repoList = searchResult.map { pagingData -> pagingData.map { it.toGithubReposUiModel() } }
                )
            } catch (e: Exception) {
                _searchRepoStateFlow.value = SearchRepoUiState(
                    isLoading = false,
                    isError = true,
                    customRemoteExceptionUiModel = (e as CustomRemoteExceptionDomainModel).toCustomExceptionRemoteUiModel()
                )
            }
        }
    }
}