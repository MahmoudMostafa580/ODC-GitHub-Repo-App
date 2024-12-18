package com.example.odcgithubrepoapp.presentation.screens.repo_search_screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarColors
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.odcgithubrepoapp.presentation.common_component.ErrorSection
import com.example.odcgithubrepoapp.presentation.common_component.shimmer.repo_list.AnimateShimmerRepoList
import com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.component.RepoItem
import com.example.odcgithubrepoapp.presentation.screens.repo_search_screen.viewmodel.RepoSearchViewModel
import com.example.odcgithubrepoapp.presentation.theme.ODCGithubRepoAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepoSearchScreen(
    onSearchResultClick: (String, String) -> Unit,
    onCloseIconClicked: () -> Unit,
) {
    val repoSearchViewModel: RepoSearchViewModel = hiltViewModel()
    val searchRepoUiState by repoSearchViewModel.searchRepoStateFlow.collectAsStateWithLifecycle()

    var textQuery by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        content = {
            SearchBar(
                modifier = Modifier
                    .padding(all = 8.dp)
                    .fillMaxWidth(),
                query = textQuery,
                onQueryChange = {
                    textQuery = it
                    if (textQuery.isEmpty()) {
                        repoSearchViewModel.stopLoading()
                    } else {
                        repoSearchViewModel.startLoading()

                    }
                    Log.d("Search State: ", "onQueryChange")
                },
                active = active,
                onActiveChange = {
                    active = it
                    Log.d("Search State: ", "onActiveChange")

                },
                onSearch = { text ->
                    Log.d("Search State: ", "onSearchClicked")

                    repoSearchViewModel.searchRepos(text)

                },

                leadingIcon = {
                    if(active){
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search icon"
                        )
                    }else{
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back arrow",
                            modifier = Modifier.clickable {
                                onCloseIconClicked()
                            }
                        )
                    }


                },
                trailingIcon = {
                    if (active) {
                        Icon(
                            modifier = Modifier.clickable {
                                repoSearchViewModel.clearResultList()
                                if (textQuery.isNotEmpty()) {
                                    textQuery = ""
                                    active = false

                                } else {
                                    active = false
//                                    onCloseIconClicked()
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
                colors = SearchBarDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.background,
                    inputFieldColors = SearchBarDefaults.inputFieldColors(cursorColor = MaterialTheme.colorScheme.onSurface),
                )
            ) {
                when {
                    searchRepoUiState.isLoading -> {
                        AnimateShimmerRepoList(
                            innerPadding = PaddingValues(0.dp)
                        )
                    }

                    searchRepoUiState.isError -> {
                        ErrorSection(
                            innerPadding = PaddingValues(0.dp),
                            customErrorExceptionUiModel = searchRepoUiState.customRemoteExceptionUiModel,
                            onRefreshButtonClicked = {
                                repoSearchViewModel.searchRepos(textQuery)
                            }
                        )
                    }

                    searchRepoUiState.repoList.collectAsLazyPagingItems().itemCount != 0 -> {
                        val result = searchRepoUiState.repoList.collectAsLazyPagingItems()
                        Log.d("Search Result: ", "${result.itemCount}")

                        LazyColumn(
                            Modifier
                                .padding(PaddingValues(0.dp))
                                .padding(horizontal = 8.dp)
                        ) {
                            items(
                                count = result.itemCount
                            ) { index ->
                                val githubRepoUiModel = result[index]
                                if (githubRepoUiModel != null) {
                                    RepoItem(
                                        githubRepoUiModel = githubRepoUiModel,
                                        onRepoItemClicked = onSearchResultClick
                                    )
                                }

                            }
                        }
                    }


                }
            }
        })
}


@Preview(showBackground = true)
@Composable
fun PreviewSearchScreen() {
    ODCGithubRepoAppTheme {
//        RepoSearchScreen(
////            onSearchResultClick = { _, _ -> },
////            onCloseIconClicked = {}
//        )
    }
}