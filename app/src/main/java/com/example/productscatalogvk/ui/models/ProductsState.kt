package com.example.productscatalogvk.ui.models

import com.example.productscatalogvk.domain.models.Product

sealed interface ProductsState {

    object Loading : ProductsState

    data class Content(
        val products: List<Product>
    ) : ProductsState

    class Error: ProductsState

    class Empty: ProductsState

}