package com.example.ktor.network.repository

import com.example.ktor.data.Resource
import com.example.ktor.network.model.RawGitRepository

interface GitRepoRepository {
    suspend fun getGitRepo(): Resource<RawGitRepository>
}