package com.example.productscatalogvk.data.network

import com.example.productscatalogvk.data.dto.DumRequest
import com.example.productscatalogvk.data.dto.DumResponse
import com.example.productscatalogvk.domain.api.ProductsRepository
import com.example.productscatalogvk.domain.models.Product
import com.example.productscatalogvk.utility.Resource

class ProductsRepositoryImpl(private val networkClient: NetworkClient) : ProductsRepository {

    override fun searchProducts(position: Int, limit: Int): Resource<List<Product>> {
        val response = networkClient.doRequest(DumRequest(position, limit))
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение к интернету")
            }
            200 -> {
                Resource.Success((response as DumResponse).results.map {
                    Product(it.id, it.title, it.description, it.price, it.discountPercentage, it.rating, it.stock,
                        it.brand, it.category, it.thumbnail, it.images)})
            }
            else -> {
                Resource.Error("Ошибка сервера")
            }
        }
    }
}
