package com.example.odcgithubrepoapp.presentation.screens.repoIssuesScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.odcgithubrepoapp.domain.usecase.FetchRepoIssuesListUseCase
import kotlinx.coroutines.launch

class RepoIssuesViewModel(
    private val repoIssuesListUseCase: FetchRepoIssuesListUseCase
): ViewModel() {
    fun requestRepoIssuesList(ownerName: String, repoName: String){
        viewModelScope.launch {
            try {
                val issuesList = repoIssuesListUseCase(ownerName, repoName)

            }catch (e: Exception){

            }
        }
    }
}