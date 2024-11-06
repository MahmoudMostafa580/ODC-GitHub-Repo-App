package com.mahmoud.data.repository

import com.example.odcgithubrepoapp.data.data_sources.remote.GithubRemoteDataSource
import com.example.odcgithubrepoapp.data.mapper.toGithubReposDomainModel
import com.example.odcgithubrepoapp.domain.model.GithubReposDomainModel
import com.mahmoud.domain.repository.SearchReposRepository
import javax.inject.Inject

class SearchReposImpl @Inject constructor(
    private val githubRemoteDataSource: GithubRemoteDataSource
) : SearchReposRepository {
    override suspend fun searchRepos(language: String): List<GithubReposDomainModel> {
        return githubRemoteDataSource.searchRepos(language).items.map { it.toGithubReposDomainModel() }
    }

}