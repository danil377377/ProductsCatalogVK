package com.example.productscatalogvk.data.network

import com.example.productscatalogvk.data.dto.DumResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DumApi {
    @GET("/products")
fun search(@Query("skip") position: Int, @Query("limit") limit: Int): Call<DumResponse>
}