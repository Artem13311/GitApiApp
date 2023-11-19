package com.example.ktor.data

import com.example.ktor.network.ApiRoutes
import com.example.ktor.network.model.RawGitRepository
import com.example.ktor.network.repository.GitRepoRepository
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.request.*
import javax.inject.Inject

class GitRepoRepositoryImpl @Inject constructor(
    private val  httpClient: HttpClient
) : GitRepoRepository {

    override suspend fun getGitRepo(): Resource<RawGitRepository> {
        return try{
            Resource.Success(
                httpClient.get(ApiRoutes.REPOSITORY).body()
            )

        }catch (e:Exception){
            e.printStackTrace()
            Resource.Failure(e)
        }
    }
}