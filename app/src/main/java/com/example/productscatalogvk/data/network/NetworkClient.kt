package com.example.productscatalogvk.data.network

import com.example.productscatalogvk.data.dto.Response

interface NetworkClient {
    fun doRequest(dto: Any): Response

}