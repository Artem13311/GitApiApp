package com.example.ktor.network

import android.util.Log

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import io.ktor.serialization.gson.gson


object KtorClient {

    val httpClient = HttpClient(Android){
        install(HttpTimeout){
            socketTimeoutMillis = 15000
            requestTimeoutMillis= 15000
            connectTimeoutMillis= 15000
        }
        install(Logging){
            logger = object :Logger{
                override fun log(message: String) {
                    Log.d("TAG","Log:$message")
                }
            }
        }

        install(ContentNegotiation){
            gson{
                setLenient()
                setPrettyPrinting()
            }
        }
        defaultRequest {
            contentType(Json)
            accept(Json)
        }
    }
}