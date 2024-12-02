package com.example.odcgithubrepoapp.presentation.navigation

import com.example.odcgithubrepoapp.presentation.utils.Constants.Companion.NAME_ARGUMENT_KEY
import com.example.odcgithubrepoapp.presentation.utils.Constants.Companion.OWNER_ARGUMENT_KEY
import com.example.odcgithubrepoapp.presentation.utils.Constants.Companion.REPO_DETAILS_SCREEN
import com.example.odcgithubrepoapp.presentation.utils.Constants.Companion.REPO_ISSUES_SCREEN
import com.example.odcgithubrepoapp.presentation.utils.Constants.Companion.REPO_LIST_SCREEN
import com.example.odcgithubrepoapp.presentation.utils.Constants.Companion.REPO_SEARCH_SCREEN
import com.example.odcgithubrepoapp.presentation.utils.Constants.Companion.SPLASH_SCREEN
import com.example.odcgithubrepoapp.presentation.utils.Constants.Companion.WELCOME_SCREEN


sealed class Screens(val route: String) {
    data object SplashScreen: Screens(SPLASH_SCREEN)
    data object WelcomeScreen: Screens(WELCOME_SCREEN)

    data object RepoListScreen : Screens(REPO_LIST_SCREEN)
    data object RepoDetailsScreen :
        Screens("$REPO_DETAILS_SCREEN/{$OWNER_ARGUMENT_KEY}/{$NAME_ARGUMENT_KEY}") {
        fun passOwnerAndName(owner: String, name: String): String {
            return "$REPO_DETAILS_SCREEN/$owner/$name"
        }
    }

    data object RepoIssuesScreen :
        Screens("$REPO_ISSUES_SCREEN/{$OWNER_ARGUMENT_KEY}/{$NAME_ARGUMENT_KEY}") {

        fun passOwnerAndName(owner: String, name: String): String {
            return "$REPO_ISSUES_SCREEN/$owner/$name"
        }
    }
    data object RepoSearchScreen: Screens(REPO_SEARCH_SCREEN)
}