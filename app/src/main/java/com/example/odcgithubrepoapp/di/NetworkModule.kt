package com.example.odcgithubrepoapp.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.odcgithubrepoapp.data.data_sources.remote.retrofit.api.RepoDetailsApi
import com.mahmoud.data.data_sources.remote.retrofit.api.RepoIssuesApi
import com.example.odcgithubrepoapp.data.data_sources.remote.retrofit.api.ReposSearchApi
import com.example.odcgithubrepoapp.data.data_sources.remote.retrofit.api.RepositoriesListApi
import com.mahmoud.data.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRepositoriesListApi(
        retrofit: Retrofit
    ): RepositoriesListApi {
        return retrofit.create(RepositoriesListApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepoDetailsApi(
        retrofit: Retrofit
    ): RepoDetailsApi {
        return retrofit.create(RepoDetailsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepositoryIssuesApi(
        retrofit: Retrofit
    ): RepoIssuesApi {
        return retrofit.create(RepoIssuesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepositorySearchApi(
        retrofit: Retrofit
    ): ReposSearchApi {
        return retrofit.create(ReposSearchApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
       @ApplicationContext context: Context
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(ChuckerInterceptor(context))
            .build()
    }


    @Provides
    @Singleton
    fun provideRetrofitInstance(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}