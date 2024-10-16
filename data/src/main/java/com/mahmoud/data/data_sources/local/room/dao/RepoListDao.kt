package com.mahmoud.data.data_sources.local.room.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.odcgithubrepoapp.data.data_sources.local.room.entities.ReposListEntity

@Dao
interface RepoListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReposList(repoList: List<ReposListEntity>)

    @Query("SELECT * FROM ReposListEntity")
    suspend fun getReposList(): PagingSource<Int, ReposListEntity>

    @Query("DELETE FROM ReposListEntity")
    suspend fun deleteAllRepos()
}