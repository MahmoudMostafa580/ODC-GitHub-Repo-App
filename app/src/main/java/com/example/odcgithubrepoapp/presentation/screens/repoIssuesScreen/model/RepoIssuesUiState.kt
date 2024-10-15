package com.example.odcgithubrepoapp.presentation.screens.repoIssuesScreen.model

import com.example.odcgithubrepoapp.presentation.model.CustomRemoteExceptionUiModel
import com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.model.GithubReposUiModel

data class RepoIssuesUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val customRemoteExceptionUiModel: CustomRemoteExceptionUiModel = CustomRemoteExceptionUiModel.Unknown,
    val repoList: List<RepoIssuesUiModel> = emptyList()

)