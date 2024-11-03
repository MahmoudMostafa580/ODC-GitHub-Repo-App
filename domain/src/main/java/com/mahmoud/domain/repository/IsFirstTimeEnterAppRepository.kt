package com.mahmoud.domain.repository


interface IsFirstTimeEnterAppRepository {
    suspend fun saveIsFirstTimeEnterApp(isFirstTime: Boolean)
    suspend fun readIsFirstTimeEnterApp(): Boolean?
}