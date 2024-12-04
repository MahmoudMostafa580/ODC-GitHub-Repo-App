package com.example.odcgithubrepoapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.githubreposapp.presentation.screens.repo_details_screen.RepoDetailsScreen
import com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.RepoListScreen
import com.example.odcgithubrepoapp.presentation.screens.repo_search_screen.RepoSearchScreen
import com.example.odcgithubrepoapp.presentation.screens.splash_screen.AnimatedSplashScreen
import com.example.odcgithubrepoapp.presentation.screens.welcome_screen.WelcomeScreen
import com.example.odcgithubrepoapp.presentation.utils.Constants.Companion.NAME_ARGUMENT_KEY
import com.example.odcgithubrepoapp.presentation.utils.Constants.Companion.OWNER_ARGUMENT_KEY
import com.mahmoud.githubrepos.presentation.screens.repoIssuesScreen.ReposIssuesScreen

@Composable
fun AppNavHost(
    startDestination: String
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screens.SplashScreen.route) {
            AnimatedSplashScreen(
                onAnimationFinished = {
                    navController.popBackStack()
                    navController.navigate(
                        Screens.RepoListScreen.route
                    )
                }
            )
        }
        composable(route = Screens.WelcomeScreen.route) {
            WelcomeScreen(
                onClick = {
                    navController.popBackStack()
                    navController.navigate(Screens.RepoListScreen.route)
                }
            )
        }
        composable(route = Screens.RepoListScreen.route) {
            RepoListScreen(
                onRepoItemClicked = { ownerName, name ->
                    navController.navigate(
                        Screens.RepoDetailsScreen.passOwnerAndName(
                            ownerName,
                            name
                        )
                    )
                },
                onSearchClicked = {
                    navController.navigate(Screens.RepoSearchScreen.route)
                }
            )
        }

        composable(
            route = Screens.RepoDetailsScreen.route,
            arguments = listOf(
                navArgument(OWNER_ARGUMENT_KEY) {
                    type = NavType.StringType
                },
                navArgument(NAME_ARGUMENT_KEY) {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->
            val owner = navBackStackEntry.arguments?.getString(OWNER_ARGUMENT_KEY)
            val name = navBackStackEntry.arguments?.getString(NAME_ARGUMENT_KEY)
            if (owner != null && name != null) {
                RepoDetailsScreen(
                    owner = owner,
                    name = name,
                    onShowIssuesClicked = {
                        // navigate to issues screen
                        navController.navigate(
                            Screens.RepoIssuesScreen.passOwnerAndName(
                                owner = owner,
                                name = name
                            )
                        )
                    },
                    onClickBack = {
                        navController.popBackStack()
                    }
                )
            }
        }

        composable(
            route = Screens.RepoSearchScreen.route
        ) {
            RepoSearchScreen(
                onSearchResultClick = { ownerName, name ->
                    navController.navigate(
                        Screens.RepoDetailsScreen.passOwnerAndName(
                            ownerName,
                            name
                        )
                    )
                },
                onCloseIconClicked = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = Screens.RepoIssuesScreen.route,
            arguments = listOf(
                navArgument(OWNER_ARGUMENT_KEY) {
                    type = NavType.StringType
                },
                navArgument(NAME_ARGUMENT_KEY) {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->
            val owner = navBackStackEntry.arguments?.getString(OWNER_ARGUMENT_KEY)
            val name = navBackStackEntry.arguments?.getString(NAME_ARGUMENT_KEY)
            if (owner != null && name != null) {
                ReposIssuesScreen(
                    ownerName = owner,
                    repoName = name,
                    onClickBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}