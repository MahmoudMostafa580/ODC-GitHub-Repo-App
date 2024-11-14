package com.mahmoud.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.odcgithubrepoapp.data.data_sources.remote.GithubRemoteDataSource
import com.example.odcgithubrepoapp.domain.model.GithubReposDomainModel
import com.mahmoud.data.data_sources.paging.SearchPagingSource
import com.mahmoud.domain.repository.SearchReposRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchReposImpl @Inject constructor(
    private val githubRemoteDataSource: GithubRemoteDataSource
) : SearchReposRepository {
    override suspend fun searchRepos(
        language: String
    ): Flow<PagingData<GithubReposDomainModel>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                SearchPagingSource(githubRemoteDataSource, language)
            }
        ).flow
    }
}