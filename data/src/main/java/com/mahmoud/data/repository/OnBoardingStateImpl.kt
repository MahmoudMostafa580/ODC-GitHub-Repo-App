package com.mahmoud.data.repository

import com.mahmoud.data.data_sources.local.GithubLocalDataSource
import com.mahmoud.domain.repository.OnBoardingStateRepository
import javax.inject.Inject

class OnBoardingStateImpl @Inject constructor(
    private val githubLocalDataSource: GithubLocalDataSource
) : OnBoardingStateRepository {
    override suspend fun saveOnBoardingState(completed: Boolean) {
        githubLocalDataSource.saveOnBoardingState(completed)
    }

    override suspend fun readOnBoardingState(): Boolean? {
        return githubLocalDataSource.readOnBoardingState()
    }
}