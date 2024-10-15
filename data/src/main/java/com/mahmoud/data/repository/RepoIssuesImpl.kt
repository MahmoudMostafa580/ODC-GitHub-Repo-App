package com.example.odcgithubrepoapp.data.repository

import com.example.odcgithubrepoapp.data.data_sources.remote.GithubRemoteDataSource
import com.example.odcgithubrepoapp.data.mapper.toRepoIssuesDomainModel
import com.example.odcgithubrepoapp.domain.model.RepoIssuesDomainModel
import com.example.odcgithubrepoapp.domain.repository.GithubRepoIssuesRepository
import javax.inject.Inject

class RepoIssuesImpl @Inject constructor(
    private val githubRemoteDataSource: GithubRemoteDataSource
) : GithubRepoIssuesRepository {
    override suspend fun fetchRepoIssues(owner: String, name: String): List<RepoIssuesDomainModel> {
        return githubRemoteDataSource.fetchRepoIssues(owner, name).map {
            it.toRepoIssuesDomainModel()
        }
    }
}