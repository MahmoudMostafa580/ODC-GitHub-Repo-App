package com.mahmoud.domain.repository

import kotlinx.coroutines.flow.Flow


interface OnBoardingStateRepository {

    suspend fun saveOnBoardingState(completed: Boolean)
    suspend fun readOnBoardingState(): Flow<Boolean>
}