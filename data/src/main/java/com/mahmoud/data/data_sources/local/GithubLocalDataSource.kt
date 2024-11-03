package com.mahmoud.data.data_sources.local

import android.util.Log
import com.mahmoud.data.data_sources.local.room.dao.RepoListDao
import com.example.odcgithubrepoapp.data.data_sources.local.room.entities.ReposListEntity
import com.mahmoud.data.data_sources.local.data_store.DataStorePreference
import com.mahmoud.data.mapper.toCustomRemoteExceptionDomainModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GithubLocalDataSource @Inject constructor(
    private val repoListDao: RepoListDao,
    private val dataStorePreference: DataStorePreference,
) {
    suspend fun getTrendingList(): List<ReposListEntity> {
        try{
            Log.d("Fetching data: ", "Fetch from local database")
            return repoListDao.getReposList()
        }catch (e: Exception){
            throw e.toCustomRemoteExceptionDomainModel()
        }
    }

    suspend fun insertRepos(repoList: List<ReposListEntity>) {
        repoListDao.insertReposList(repoList)
        Log.d("Fetching data: ", "Save to local database")

    }

    suspend fun saveIsFirstTimeEnterApp(isFirstTime: Boolean){
        dataStorePreference.saveIsFirstTimeEnterApp(isFirstTime)
    }

    suspend fun readIsFirstTimeEnterApp(): Boolean? {
        return dataStorePreference.readIsFirstTimeEnterApp()
    }

}