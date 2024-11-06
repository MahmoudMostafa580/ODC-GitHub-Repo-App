package com.mahmoud.domain.usecase

import com.example.odcgithubrepoapp.domain.model.GithubReposDomainModel
import com.mahmoud.domain.repository.SearchReposRepository

class SearchReposUseCase(
    private val searchReposRepository: SearchReposRepository
) {
    suspend operator fun invoke(language: String): List<GithubReposDomainModel>{
        return searchReposRepository.searchRepos(language)
    }
}