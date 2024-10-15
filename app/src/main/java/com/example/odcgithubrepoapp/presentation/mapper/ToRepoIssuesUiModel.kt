package com.example.odcgithubrepoapp.presentation.mapper

import com.example.odcgithubrepoapp.domain.model.RepoIssuesDomainModel
import com.example.odcgithubrepoapp.presentation.screens.repoIssuesScreen.model.RepoIssuesUiModel

fun RepoIssuesDomainModel.toRepoIssuesUiModel(): RepoIssuesUiModel{
    return RepoIssuesUiModel(
        title = this.title,
        state = this.state,
        author_association = this.author_association,
        created_at = this.created_at
    )
}