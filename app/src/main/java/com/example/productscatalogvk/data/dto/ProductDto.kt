package com.example.productscatalogvk.data.dto

data class ProductDto(
    val id: Int,
    val title: String,
    val description: String,
    val price: Float,
    val discountPercentage: Float,
    val rating:Float,
    val stock: Float,
    val brand: String,
    val category: String,
    val thumbnail: String,
    val images: ArrayList<String>
)