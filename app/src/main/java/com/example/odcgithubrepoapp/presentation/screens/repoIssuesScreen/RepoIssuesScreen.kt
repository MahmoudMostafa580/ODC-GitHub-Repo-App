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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.odcgithubrepoapp.R
import com.example.odcgithubrepoapp.presentation.common_component.AppBar
import com.example.odcgithubrepoapp.presentation.theme.ODCGithubRepoAppTheme
import com.mahmoud.githubrepos.presentation.screens.repoIssuesScreen.component.IssueItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReposIssuesScreen(
    issues: List<String> = List(100) { "$it" }
) {
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background),
        topBar = {
            AppBar(title = R.string.app_name, showBackButton = true)
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
    ODCGithubRepoAppTheme {
        ReposIssuesScreen()
    }
}
