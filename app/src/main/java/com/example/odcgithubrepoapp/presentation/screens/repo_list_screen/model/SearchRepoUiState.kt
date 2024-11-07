package com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.model

import androidx.paging.PagingData
import com.example.odcgithubrepoapp.presentation.model.CustomRemoteExceptionUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow


data class SearchRepoUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val customRemoteExceptionUiModel: CustomRemoteExceptionUiModel = CustomRemoteExceptionUiModel.Unknown,
    val repoList: Flow<PagingData<GithubReposUiModel>> = emptyFlow()
)
