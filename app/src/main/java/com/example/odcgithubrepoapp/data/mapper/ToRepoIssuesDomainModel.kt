package com.example.odcgithubrepoapp.data.mapper

import com.example.odcgithubrepoapp.data.data_sources.remote.retrofit.data_model.repo_issues.RepoIssuesDataModelItem
import com.example.odcgithubrepoapp.domain.model.RepoIssuesDomainModel

fun RepoIssuesDataModelItem.toRepoIssuesDomainModel(): RepoIssuesDomainModel{
    return RepoIssuesDomainModel(
        title = this.title,
        state = this.state,
        author_association = this.author_association,
        created_at = this.created_at
    )
}