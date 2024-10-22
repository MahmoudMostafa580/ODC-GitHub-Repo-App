package com.mahmoud.data.mapper

import com.example.odcgithubrepoapp.data.data_sources.local.room.entities.ReposListEntity
import com.example.odcgithubrepoapp.data.data_sources.remote.retrofit.data_model.repo_list.Item

fun Item.toRepoEntity(): ReposListEntity {
    return ReposListEntity(
        id = this.id,
        name = this.name,
        avatar = this.owner.avatar_url,
        ownerName = this.owner.login,
        starsCount = this.stargazers_count,
        description = this.description
    )

}