package com.example.productscatalogvk.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.productscatalogvk.data.dto.DumRequest
import com.example.productscatalogvk.data.dto.Response

class RetrofitNetworkClient(private val context: Context, val DumService: DumApi) : NetworkClient {

    override fun doRequest(dto: Any): Response {
        if (isConnected() == false) {
            return Response().apply { resultCode = -1 }
        }
        if (dto !is DumRequest) {
            return Response().apply { resultCode = 400 }
        }

        val response = DumService.search(dto.position, dto.limit).execute()
        val body = response.body()
        return if (body != null) {
            body.apply { resultCode = response.code() }
        } else {
            Response().apply { resultCode = response.code() }
        }
    }
    private fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
            }
        }
        return false
    }
}