package com.example.odcgithubrepoapp.presentation.screens.repoIssuesScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.odcgithubrepoapp.domain.model.CustomRemoteExceptionDomainModel
import com.mahmoud.domain.usecase.FetchRepoIssuesListUseCase
import com.example.odcgithubrepoapp.presentation.mapper.toCustomExceptionRemoteUiModel
import com.example.odcgithubrepoapp.presentation.mapper.toRepoIssuesUiModel
import com.example.odcgithubrepoapp.presentation.screens.repoIssuesScreen.model.RepoIssuesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoIssuesViewModel @Inject constructor(
    private val repoIssuesListUseCase: FetchRepoIssuesListUseCase
) : ViewModel() {

    private val _repoIssuesStateFlow =
        MutableStateFlow<RepoIssuesUiState>(RepoIssuesUiState(isLoading = true))
    val repoIssuesStateFlow: StateFlow<RepoIssuesUiState> = _repoIssuesStateFlow.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _repoIssuesStateFlow.value = RepoIssuesUiState(
            isLoading = false,
            isError = true,
            customRemoteExceptionUiModel = (throwable as CustomRemoteExceptionDomainModel).toCustomExceptionRemoteUiModel()
        )
    }

    fun requestRepoIssuesList(ownerName: String, repoName: String) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            try {
                val issuesList = repoIssuesListUseCase(ownerName, repoName)
                _repoIssuesStateFlow.value = RepoIssuesUiState(
                    isLoading = false,
                    issuesList = issuesList.map { it.toRepoIssuesUiModel() }
                )

            } catch (e: Exception) {
                _repoIssuesStateFlow.value = RepoIssuesUiState(
                    isLoading = false,
                    isError = true,
                    customRemoteExceptionUiModel = (e as CustomRemoteExceptionDomainModel).toCustomExceptionRemoteUiModel()

                )
            }
        }
    }
}