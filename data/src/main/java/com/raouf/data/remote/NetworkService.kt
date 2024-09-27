package com.raouf.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkService  {

    @GET("products/category/{categoryName}")
    suspend fun getProducts (
        @Path("categoryName") categoryName : String
    ) : ProductDto

    @GET("products/{id}")
    suspend fun getProductById(
        @Path("id") id : Int
    ) : ProductDtoItem

    @GET("products/categories")
    suspend fun getCategories () : ArrayList<String>








}