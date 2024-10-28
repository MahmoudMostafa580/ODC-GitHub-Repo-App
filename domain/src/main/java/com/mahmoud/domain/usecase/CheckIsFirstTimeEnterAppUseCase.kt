package com.mahmoud.domain.usecase

import com.mahmoud.domain.repository.IsFirstTimeEnterAppRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckIsFirstTimeEnterAppUseCase @Inject constructor(
    private val isFirstTimeEnterApp: IsFirstTimeEnterAppRepository
) {

    suspend fun saveIsFirstTime(isFirstTime: Boolean){
        isFirstTimeEnterApp.saveIsFirstTimeEnterApp(isFirstTime)
    }

    suspend fun readIsFirstTime(): Flow<Boolean>{
        return isFirstTimeEnterApp.readIsFirstTimeEnterApp()
    }
}