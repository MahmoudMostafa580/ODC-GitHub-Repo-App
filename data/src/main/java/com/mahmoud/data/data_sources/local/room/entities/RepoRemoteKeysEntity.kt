package com.mahmoud.data.data_sources.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RepoRemoteKeysEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val prevPage: Int?,
    val nextPage: Int?
)