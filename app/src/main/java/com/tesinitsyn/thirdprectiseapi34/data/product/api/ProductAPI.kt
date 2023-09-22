package com.tesinitsyn.thirdprectiseapi34.data.product.api

import com.tesinitsyn.thirdprectiseapi34.data.product.model.Products
import retrofit2.http.GET

interface ProductAPI {
    @GET("products")
    suspend fun getAllProducts(): Products
}