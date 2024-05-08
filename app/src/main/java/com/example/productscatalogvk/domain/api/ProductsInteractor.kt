package com.example.productscatalogvk.domain.api

import com.example.productscatalogvk.domain.models.Product

interface ProductsInteractor {
    fun searchProducts(position: Int, limit: Int, consumer: TracksConsumer)

    interface TracksConsumer {
        fun consume(foundProducts: List<Product>?, errorMessage: String?)
    }
}