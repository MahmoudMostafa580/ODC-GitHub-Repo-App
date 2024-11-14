package com.example.odcgithubrepoapp.data.data_sources.remote.retrofit.api

import com.example.odcgithubrepoapp.data.data_sources.remote.retrofit.data_model.repo_list.GithubReposDataModel
import com.mahmoud.data.Constants.Companion.GITHUB_REPOS_SEARCH_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ReposSearchApi {
    @GET(GITHUB_REPOS_SEARCH_ENDPOINT)
    suspend fun searchRepos(
        @Query("q") language: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<GithubReposDataModel>
}