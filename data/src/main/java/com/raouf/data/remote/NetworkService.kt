package com.raouf.data.remote

import retrofit2.http.GET

interface NetworkService  {

    @GET("products")
    suspend fun getProducts () : ProductDto
}