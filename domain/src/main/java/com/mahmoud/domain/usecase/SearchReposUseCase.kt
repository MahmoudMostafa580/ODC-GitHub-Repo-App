package com.mahmoud.domain.usecase

import androidx.paging.PagingData
import com.example.odcgithubrepoapp.domain.model.GithubReposDomainModel
import com.mahmoud.domain.repository.SearchReposRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchReposUseCase @Inject constructor(
    private val searchReposRepository: SearchReposRepository
) {
    suspend operator fun invoke(language: String): Flow<PagingData<GithubReposDomainModel>>{
        return searchReposRepository.searchRepos(language)
    }
}