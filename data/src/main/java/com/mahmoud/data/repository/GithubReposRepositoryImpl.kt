package com.mahmoud.data.repository

import com.mahmoud.data.data_sources.local.GithubLocalDataSource
import com.example.odcgithubrepoapp.data.data_sources.remote.GithubRemoteDataSource
import com.example.odcgithubrepoapp.data.mapper.toRepoDetailsDomainModel
import com.example.odcgithubrepoapp.domain.model.GithubReposDomainModel
import com.example.odcgithubrepoapp.domain.model.RepoDetailsDomainModel
import com.mahmoud.domain.repository.GithubReposRepository
import com.mahmoud.data.mapper.toGithubReposDomainModel
import com.mahmoud.data.mapper.toRepoEntity
import javax.inject.Inject

class GithubReposRepositoryImpl @Inject constructor(
    private val githubRemoteDataSource: GithubRemoteDataSource,
    private val githubLocalDataSource: GithubLocalDataSource,
) : GithubReposRepository {

    override suspend fun refreshReposList() {
        val remoteRepoList = githubRemoteDataSource.fetchRepositoriesList().items
        githubLocalDataSource.insertRepos(remoteRepoList.map { it.toRepoEntity() })
    }

    override suspend fun fetchReposList(): List<GithubReposDomainModel> {
        return githubLocalDataSource.getTrendingList().map { it.toGithubReposDomainModel() }
    }

    override suspend fun fetchRepoDetails(ownerName: String, name: String): RepoDetailsDomainModel {
        return githubRemoteDataSource.fetchRepoDetails(ownerName, name).toRepoDetailsDomainModel()
    }
}