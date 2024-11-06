package com.mahmoud.domain.repository

import androidx.paging.PagingData
import com.example.odcgithubrepoapp.domain.model.GithubReposDomainModel
import kotlinx.coroutines.flow.Flow

interface SearchReposRepository {
    suspend fun searchRepos(language: String, perPage: Int): Flow<PagingData<GithubReposDomainModel>>
}