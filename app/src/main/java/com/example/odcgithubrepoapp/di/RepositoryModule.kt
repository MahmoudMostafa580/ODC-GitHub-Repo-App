package com.example.odcgithubrepoapp.di

import com.mahmoud.data.data_sources.local.GithubLocalDataSource
import com.example.odcgithubrepoapp.data.data_sources.remote.GithubRemoteDataSource
import com.mahmoud.data.repository.GithubReposRepositoryImpl
import com.mahmoud.data.repository.RepoIssuesImpl
import com.mahmoud.domain.repository.GithubRepoIssuesRepository
import com.mahmoud.data.repository.IsFirstTimeEnterAppImpl
import com.mahmoud.domain.repository.GithubReposRepository
import com.mahmoud.domain.repository.IsFirstTimeEnterAppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideGithubReposRepository(
        githubRemoteDataSource: GithubRemoteDataSource,
        localDataSource: GithubLocalDataSource
    ): GithubReposRepository {
        return GithubReposRepositoryImpl(githubRemoteDataSource, localDataSource)
    }


    @Provides
    @Singleton
    fun provideRepoIssuesRepository(
        githubRemoteDataSource: GithubRemoteDataSource
    ): GithubRepoIssuesRepository {
        return RepoIssuesImpl(githubRemoteDataSource)
    }

    @Provides
    @Singleton
    fun provideDataStoreRepository(
        githubLocalDataSource: GithubLocalDataSource
    ): IsFirstTimeEnterAppRepository{
        return IsFirstTimeEnterAppImpl(githubLocalDataSource)
    }
}