package com.mahmoud.githubrepos.presentation.screens.repoIssuesScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mahmoud.githubrepos.presentation.common_components.AppBar
import com.mahmoud.githubrepos.presentation.screens.repoIssuesScreen.component.IssueItem
import com.mahmoud.githubrepos.presentation.theme.theme.GiyHubReposTheme

@Composable
fun ReposIssuesScreen(
    issues: List<String> = List(100) { "$it" }
) {
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background),
        topBar = {
            AppBar(title = "Repository Issues", showBackButton = true)
        },
        content = { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(bottom = 16.dp)
            ) {
                items(items = issues) { issueItem ->
                    IssueItem()

                }
            }
        }
    )

}

@Preview(showBackground = true)
@Composable
fun RepoIssuesScreen() {
    GiyHubReposTheme {
        ReposIssuesScreen()
    }
}