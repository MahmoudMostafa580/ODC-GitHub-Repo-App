package com.mahmoud.data.data_sources.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.odcgithubrepoapp.data.data_sources.local.room.entities.ReposListEntity
import com.mahmoud.data.data_sources.local.room.dao.RepoListDao
import com.mahmoud.data.data_sources.local.room.dao.RepoRemoteKeysDao
import com.mahmoud.data.data_sources.local.room.entities.RepoRemoteKeysEntity

@Database(
    entities = [ReposListEntity::class, RepoRemoteKeysEntity::class],
    version = 1,
    exportSchema = false
)
abstract class GithubRepositoriesDatabase : RoomDatabase() {
    abstract fun repoListDao(): RepoListDao
    abstract fun repoRemoteKeysDao(): RepoRemoteKeysDao
}