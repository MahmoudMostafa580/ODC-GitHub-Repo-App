package com.example.odcgithubrepoapp.domain.usecase

import com.example.odcgithubrepoapp.domain.model.RepoIssuesDomainModel
import com.example.odcgithubrepoapp.domain.repository.GithubRepoIssuesRepository

class FetchRepoIssuesListUseCase(
    private val githubRepoIssuesRepository: GithubRepoIssuesRepository
) {
    suspend operator fun invoke(owner: String, name: String): List<RepoIssuesDomainModel>{
        return githubRepoIssuesRepository.fetchRepoIssues(owner, name)
    }
}