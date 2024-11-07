package com.example.odcgithubrepoapp.presentation.screens.repo_search_screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.odcgithubrepoapp.presentation.theme.ODCGithubRepoAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepoSearchScreen(
    onSearchResultClick: (String, String) -> Unit
) {

    var textQuery by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(true) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        content = {
            SearchBar(
                modifier = Modifier
                    .padding(all = 8.dp)
                    .fillMaxWidth(),
                query = textQuery,
                onQueryChange = {
                    textQuery = it
                },
                active = active,
                onActiveChange = {
                    active = it
                },
                onSearch = { text ->
                    Log.d("Search text", text)
                    active = false
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search icon"
                    )

                },
                trailingIcon = {
                    if (active) {
                        Icon(
                            modifier = Modifier.clickable {
                                if(textQuery.isNotEmpty()){
                                    textQuery = ""
                                }else{
                                    active = false
                                }
                            },
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close Icon"
                        )
                    }

                },
                placeholder = {
                    Text(text = "Search by language")
                },

                ) { }
        }
    )

}

@Preview(showBackground = true)
@Composable
fun PreviewSearchScreen() {
    ODCGithubRepoAppTheme {
        RepoSearchScreen(
            onSearchResultClick = { _, _ -> }
        )
    }
}