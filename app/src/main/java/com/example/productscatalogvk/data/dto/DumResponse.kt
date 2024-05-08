package com.example.productscatalogvk.data.dto

import com.google.gson.annotations.SerializedName

class DumResponse(
    @SerializedName("products")
    val results: ArrayList<ProductDto>
):Response()