package com.example.odcgithubrepoapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.example.odcgithubrepoapp.presentation.navigation.AppNavHost
import com.example.odcgithubrepoapp.presentation.screens.welcome_screen.viewmodel.SplashViewModel
import com.example.odcgithubrepoapp.presentation.theme.ODCGithubRepoAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashViewModel: SplashViewModel by viewModels()
        installSplashScreen().setKeepOnScreenCondition{
            !splashViewModel.isLoading.value
        }
        setContent {
            ODCGithubRepoAppTheme {
                val screen by splashViewModel.startDestination
                screen?.let {
                    AppNavHost(startDestination = it)
                }
            }
        }
    }
}

