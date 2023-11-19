package com.example.ktor.di

import com.example.ktor.network.repository.GitRepoRepository
import com.example.ktor.data.GitRepoRepositoryImpl
import com.example.ktor.network.repository.GitRepoHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient


@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @Provides
    fun getHttpClient(httpClient: GitRepoHttpClient):HttpClient = httpClient.getHttpClient()

    @Provides
    fun getGitRepoRepository(impl:GitRepoRepositoryImpl): GitRepoRepository = impl
}