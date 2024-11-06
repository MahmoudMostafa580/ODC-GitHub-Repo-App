package com.mahmoud.domain.usecase

import com.example.odcgithubrepoapp.domain.model.RepoIssuesDomainModel
import com.mahmoud.domain.repository.GithubRepoIssuesRepository
import javax.inject.Inject

class FetchRepoIssuesListUseCase @Inject constructor(
    private val githubRepoIssuesRepository: GithubRepoIssuesRepository
) {
    suspend operator fun invoke(owner: String, name: String): List<RepoIssuesDomainModel>{
        return githubRepoIssuesRepository.fetchRepoIssues(owner, name)
    }
}