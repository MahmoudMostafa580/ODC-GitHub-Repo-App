package com.example.odcgithubrepoapp.presentation.screens.welcome_screen

import androidx.annotation.DrawableRes
import com.example.odcgithubrepoapp.R

sealed class OnBoardingPage(
    @DrawableRes val image: Int,
    val title: String,
    val description: String
) {
    data object First: OnBoardingPage(
        image = R.drawable.ic_github_placeholser,
        title = "Browse",
        description = "Browse All GitHub Repos via Easy User Interface"
    )

    data object Second: OnBoardingPage(
        image = R.drawable.ic_search,
        title = "Search",
        description = "Search GitHub Repos by language"
    )

    data object Third: OnBoardingPage(
        image = R.drawable.ic_issues,
        title = "Issues",
        description = "Browse All GitHub Repos' issues via Easy User Interface"
    )

}
