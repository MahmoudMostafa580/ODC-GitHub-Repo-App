package com.example.odcgithubrepoapp.data.data_sources.remote.retrofit.data_model.repo_issues

data class Label(
    val color: String,
    val default: Boolean,
    val description: String,
    val id: Int,
    val name: String,
    val node_id: String,
    val url: String
)