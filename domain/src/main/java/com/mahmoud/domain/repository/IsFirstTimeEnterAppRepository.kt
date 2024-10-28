package com.mahmoud.domain.repository

import kotlinx.coroutines.flow.Flow

interface IsFirstTimeEnterAppRepository {
    suspend fun saveIsFirstTimeEnterApp(isFirstTime: Boolean)
    suspend fun readIsFirstTimeEnterApp(): Flow<Boolean>
}