package com.example.odcgithubrepoapp.data.data_sources.remote.retrofit.api

import com.example.odcgithubrepoapp.data.data_sources.remote.retrofit.data_model.repo_issues.RepoIssuesDataModel
import com.mahmoud.data.Constants.Companion.OWNER_KEY
import com.mahmoud.data.Constants.Companion.REPO_NAME_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RepoIssuesApi {
    @GET("repos/{$OWNER_KEY}/{$REPO_NAME_KEY}/issues")
    suspend fun fetchRepoIssues(
        @Path(OWNER_KEY) ownerName:String,
        @Path(REPO_NAME_KEY) repoName: String
    ): Response<RepoIssuesDataModel>
}