package com.example.odcgithubrepoapp.presentation.screens.welcome_screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahmoud.domain.usecase.CheckOnBoardingStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val checkOnBoardingStateUseCase: CheckOnBoardingStateUseCase
): ViewModel() {

    fun saveOnBoardingState(completed: Boolean){
        viewModelScope.launch(Dispatchers.IO) {
            checkOnBoardingStateUseCase.saveOnBoardingState(completed = completed)
        }
    }
}