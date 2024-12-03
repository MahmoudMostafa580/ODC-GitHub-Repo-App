package com.mahmoud.domain.repository


interface OnBoardingStateRepository {

    suspend fun saveOnBoardingState(completed: Boolean)
    suspend fun readOnBoardingState(): Boolean?
}