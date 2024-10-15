package com.mahmoud.githubrepos.presentation.screens.repoIssuesScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.githubreposapp.presentation.common_components.shimmer.issues.AnimateShimmerIssuesList
import com.example.odcgithubrepoapp.R
import com.example.odcgithubrepoapp.presentation.common_component.AppBar
import com.example.odcgithubrepoapp.presentation.common_component.ErrorSection
import com.example.odcgithubrepoapp.presentation.screens.repoIssuesScreen.viewmodel.RepoIssuesViewModel
import com.mahmoud.githubrepos.presentation.screens.repoIssuesScreen.component.IssueItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReposIssuesScreen(
    ownerName: String,
    repoName: String,
    onClickBack: () -> Unit
) {

    val repoIssuesViewModel: RepoIssuesViewModel = hiltViewModel()
    LaunchedEffect(Unit) {
        repoIssuesViewModel.requestRepoIssuesList(ownerName, repoName)
    }

    val repoIssuesUiState by repoIssuesViewModel.repoIssuesStateFlow.collectAsStateWithLifecycle()

    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .padding(horizontal = 4.dp),
        topBar = {
            AppBar(
                title = R.string.issues_app_bar_title, showBackButton = true,
                onBackButtonClicked = onClickBack
            )
        },
        content = { innerPadding ->
            when {
                repoIssuesUiState.isLoading -> {
                    AnimateShimmerIssuesList(innerPadding)
                }

                repoIssuesUiState.isError -> {
                    ErrorSection(
                        innerPadding = innerPadding,
                        customErrorExceptionUiModel = repoIssuesUiState.customRemoteExceptionUiModel,
                        onRefreshButtonClicked = {
                            repoIssuesViewModel.requestRepoIssuesList(
                                ownerName = ownerName,
                                repoName = repoName
                            )
                        }
                    )
                }

                repoIssuesUiState.issuesList.isNotEmpty() -> {
                    LazyColumn(
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(bottom = 16.dp)
                    ) {
                        items(items = repoIssuesUiState.issuesList) { repoIssuesUiModel ->
                            IssueItem(repoIssuesUiModel = repoIssuesUiModel)

                        }
                    }
                }
            }

        }
    )

}

//@Preview(showBackground = true)
//@Composable
//fun RepoIssuesScreen() {
//    ODCGithubRepoAppTheme {
//        ReposIssuesScreen(
//            "", "", onRetryButtonClicked = {})
//    }
//}
