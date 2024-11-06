package com.mahmoud.domain.repository

import com.example.odcgithubrepoapp.domain.model.GithubReposDomainModel

interface SearchReposRepository {
    suspend fun searchRepos(language: String, perPage: Int): List<GithubReposDomainModel>
}