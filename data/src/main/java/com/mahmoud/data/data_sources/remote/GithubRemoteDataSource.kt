package com.example.odcgithubrepoapp.data.data_sources.remote

import android.util.Log
import com.example.odcgithubrepoapp.data.data_sources.remote.retrofit.api.RepoDetailsApi
import com.example.odcgithubrepoapp.data.data_sources.remote.retrofit.api.RepoIssuesApi
import com.example.odcgithubrepoapp.data.data_sources.remote.retrofit.api.ReposSearchApi
import com.example.odcgithubrepoapp.data.data_sources.remote.retrofit.api.RepositoriesListApi
import com.example.odcgithubrepoapp.data.data_sources.remote.retrofit.data_model.repo_details.RepoDetailsDataModel
import com.example.odcgithubrepoapp.data.data_sources.remote.retrofit.data_model.repo_issues.RepoIssuesDataModel
import com.example.odcgithubrepoapp.data.data_sources.remote.retrofit.data_model.repo_list.GithubReposDataModel
import com.mahmoud.data.mapper.toCustomRemoteExceptionDomainModel
import javax.inject.Inject

class GithubRemoteDataSource @Inject constructor(
    private val repositoryListApi: RepositoriesListApi,
    private val repositoryDetailsApi: RepoDetailsApi,
    private val repositoryIssuesApi: RepoIssuesApi,
    private val repositorySearchApi: ReposSearchApi
) {

    suspend fun fetchRepositoriesList(): GithubReposDataModel {
        try {
            Log.d("Fetching data: ", "Fetching from API")
            return repositoryListApi.fetchRepositoriesList().body() as GithubReposDataModel
        } catch (e: Exception) {
            throw e.toCustomRemoteExceptionDomainModel()
        }
    }

    suspend fun fetchRepoDetails(ownerName: String, name: String): RepoDetailsDataModel {
        try {
            return repositoryDetailsApi.fetchRepoDetails(ownerName, name)
                .body() as RepoDetailsDataModel
        } catch (e: Exception) {
            // Convert and rethrow the exception as a custom remote exception
            throw e.toCustomRemoteExceptionDomainModel()
        }
    }

    suspend fun fetchRepoIssues(ownerName: String, repoName: String): RepoIssuesDataModel {
        try {
            return repositoryIssuesApi.fetchRepoIssues(ownerName, repoName)
                .body() as RepoIssuesDataModel
        } catch (e: Exception){
            throw e.toCustomRemoteExceptionDomainModel()
        }
    }

    suspend fun searchRepos(language: String): GithubReposDataModel{
        try {
            return repositorySearchApi.searchRepos(language).body() as GithubReposDataModel
        }catch (e: Exception){
            throw e.toCustomRemoteExceptionDomainModel()
        }
    }
}