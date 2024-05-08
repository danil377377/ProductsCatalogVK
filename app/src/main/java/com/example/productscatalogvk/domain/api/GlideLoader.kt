package com.example.productscatalogvk.domain.api

import android.view.View
import android.widget.ImageView

interface GlideLoader {
    fun loadImage( url: String, imageView: ImageView, itemView: View)
}