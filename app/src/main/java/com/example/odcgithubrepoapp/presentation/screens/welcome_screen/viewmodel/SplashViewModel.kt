package com.example.odcgithubrepoapp.presentation.screens.welcome_screen.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.odcgithubrepoapp.presentation.navigation.Screens
import com.mahmoud.domain.usecase.CheckOnBoardingStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val checkOnBoardingStateUseCase: CheckOnBoardingStateUseCase
) : ViewModel() {

    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination: MutableState<String?> = mutableStateOf(null)
    val startDestination: State<String?> = _startDestination

    init {
        viewModelScope.launch {
            checkOnBoardingStateUseCase.readOnBoardingState().collect{ completed ->
                if (completed){
                    _startDestination.value = Screens.RepoListScreen.route
                }else{
                    _startDestination.value = Screens.WelcomeScreen.route
                }
            }

            _isLoading.value = false
        }
    }
}