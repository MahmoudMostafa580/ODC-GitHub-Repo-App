package com.example.odcgithubrepoapp.data.data_sources.remote.retrofit.api

import com.example.odcgithubrepoapp.data.data_sources.remote.retrofit.data_model.repo_issues.RepoIssuesDataModel
import com.example.odcgithubrepoapp.data.data_sources.remote.retrofit.data_model.repo_list.GithubReposDataModel
import com.mahmoud.data.Constants.Companion.GITHUB_REPOS_ENDPOINT
import com.mahmoud.data.Constants.Companion.GITHUB_REPOS_SEARCH_ENDPOINT
import com.mahmoud.data.Constants.Companion.OWNER_KEY
import com.mahmoud.data.Constants.Companion.REPO_NAME_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ReposSearchApi {
    @GET(GITHUB_REPOS_SEARCH_ENDPOINT)
    suspend fun searchRepos(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<GithubReposDataModel>
}