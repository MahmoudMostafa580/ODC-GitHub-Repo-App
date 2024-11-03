package com.mahmoud.data.repository

import com.mahmoud.data.data_sources.local.GithubLocalDataSource
import com.mahmoud.domain.repository.IsFirstTimeEnterAppRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsFirstTimeEnterAppImpl @Inject constructor(
    private val githubLocalDataSource: GithubLocalDataSource
): IsFirstTimeEnterAppRepository {
    override suspend fun saveIsFirstTimeEnterApp(isFirstTime: Boolean) {
        githubLocalDataSource.saveIsFirstTimeEnterApp(isFirstTime)
    }

    override suspend fun readIsFirstTimeEnterApp(): Boolean? {
        return githubLocalDataSource.readIsFirstTimeEnterApp()
    }
}