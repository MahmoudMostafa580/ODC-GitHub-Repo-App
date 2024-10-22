package com.mahmoud.data.mapper

import com.example.odcgithubrepoapp.data.data_sources.local.room.entities.ReposListEntity
import com.example.odcgithubrepoapp.domain.model.GithubReposDomainModel

fun ReposListEntity.toGithubReposDomainModel(): GithubReposDomainModel {
    return GithubReposDomainModel(
        id = this.id.toLong(),
        name = this.name,
        ownerName = this.ownerName,
        avatar = this.avatar,
        stars = this.starsCount,
        description = this.description
    )
}