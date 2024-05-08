package com.example.productscatalogvk.data.network

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.productscatalogvk.R
import com.example.productscatalogvk.domain.api.GlideLoader

class GlideLoaderImpl() : GlideLoader {
    override fun loadImage( url: String, imageView: ImageView, itemView: View) {
        Glide.with(itemView)
            .load(url)
            .placeholder(R.drawable.ic_android_black_24dp)
            .into(imageView)
    }
}