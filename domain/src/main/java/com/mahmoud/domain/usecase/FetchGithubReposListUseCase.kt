package com.mahmoud.domain.usecase

import com.example.odcgithubrepoapp.domain.model.GithubReposDomainModel
import com.mahmoud.domain.repository.GithubReposRepository
import javax.inject.Inject

class FetchGithubReposListUseCase @Inject constructor(
    private val githubReposRepository: GithubReposRepository
) {
    suspend fun refreshRepoList(){
        githubReposRepository.refreshReposList()
    }

    suspend fun fetchRepoList(): List<GithubReposDomainModel> {
        return githubReposRepository.fetchReposList()
    }
}
