package com.example.odcgithubrepoapp.data.data_sources.remote.retrofit.api

import com.example.odcgithubrepoapp.data.data_sources.remote.retrofit.data_model.repo_list.GithubReposDataModel
import com.mahmoud.data.Constants.Companion.GITHUB_REPOS_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoriesListApi {
    @GET(GITHUB_REPOS_ENDPOINT)
    suspend fun fetchRepositoriesList(
    ): Response<GithubReposDataModel>
}