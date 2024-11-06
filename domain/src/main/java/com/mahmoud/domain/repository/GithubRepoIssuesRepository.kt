package com.mahmoud.domain.repository

import com.example.odcgithubrepoapp.domain.model.RepoIssuesDomainModel

interface GithubRepoIssuesRepository {
    suspend fun fetchRepoIssues(owner: String, name: String): List<RepoIssuesDomainModel>
}