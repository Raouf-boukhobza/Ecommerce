package com.raouf.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkService {

    @GET("products/category/{categoryName}")
    suspend fun getProducts(
        @Path("categoryName") categoryName: String,
        @Query ("sortBy") sortBy : String = "discountPercentage",
        @Query ("order") order : String = "desc",
    ): ProductDto

    @GET("products/{id}")
    suspend fun getProductById(
        @Path("id") id: Int
    ): ProductDtoItem


}