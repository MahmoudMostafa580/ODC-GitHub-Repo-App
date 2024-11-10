package com.mahmoud.data.data_sources.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.odcgithubrepoapp.data.data_sources.remote.GithubRemoteDataSource
import com.example.odcgithubrepoapp.data.mapper.toGithubReposDomainModel
import com.example.odcgithubrepoapp.domain.model.GithubReposDomainModel
import com.mahmoud.data.mapper.toCustomRemoteExceptionDomainModel

class SearchPagingSource(
    private val githubRemoteDataSource: GithubRemoteDataSource,
    private val language: String
) : PagingSource<Int, GithubReposDomainModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GithubReposDomainModel> {
        val currentPage = params.key ?: 1
        return try {
            val searchResponse = githubRemoteDataSource.searchRepos(language, currentPage, perPage = 20)
            val endOfPaginationReached = searchResponse.items.isEmpty()
            if (searchResponse.items.isNotEmpty()) {
                LoadResult.Page(
                    data = searchResponse.items.map { it.toGithubReposDomainModel() },
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (endOfPaginationReached) null else currentPage + 1
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e.toCustomRemoteExceptionDomainModel())
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GithubReposDomainModel>): Int? {
        return state.anchorPosition
    }

}