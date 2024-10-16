package com.mahmoud.data.data_sources.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mahmoud.data.data_sources.local.room.entities.RepoRemoteKeysEntity

@Dao
interface RepoRemoteKeysDao {

    @Query("SELECT * FROM RepoRemoteKeysEntity WHERE id = :id")
    suspend fun getRemoteKeys(id: Long): RepoRemoteKeysEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<RepoRemoteKeysEntity>)

    @Query("DELETE FROM RepoRemoteKeysEntity")
    suspend fun deleteAllRemoteKeys()
}