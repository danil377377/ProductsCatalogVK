package com.example.productscatalogvk.domain.api

import com.example.productscatalogvk.domain.models.Product
import com.example.productscatalogvk.utility.Resource

interface ProductsRepository {
    fun searchProducts(position: Int, limit: Int): Resource<List<Product>>
}