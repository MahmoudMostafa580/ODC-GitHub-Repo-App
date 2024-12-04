package com.mahmoud.domain.usecase

import com.mahmoud.domain.repository.OnBoardingStateRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckOnBoardingStateUseCase @Inject constructor(
    private val onBoardingState: OnBoardingStateRepository
) {

    suspend fun saveOnBoardingState(completed: Boolean){
        onBoardingState.saveOnBoardingState(completed)
    }

    suspend fun readOnBoardingState(): Flow<Boolean> {
        return onBoardingState.readOnBoardingState()
    }
}