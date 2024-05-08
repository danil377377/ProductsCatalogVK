package com.example.productscatalogvk.ui.fragmentProducts

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.productscatalogvk.R
import com.example.productscatalogvk.domain.api.GlideLoader
import com.example.productscatalogvk.domain.models.Product
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject



class ProductsViewHolder(itemView: View ): RecyclerView.ViewHolder(itemView), KoinComponent {
    val glideLoader: GlideLoader by inject()
    private val productIcon: ImageView = itemView.findViewById(R.id.product_icon)
    private val title: TextView = itemView.findViewById(R.id.title)
    private val description: TextView = itemView.findViewById(R.id.description)

    @SuppressLint("SetTextI18n")
    fun bind(model: Product) {
        glideLoader.loadImage(model.thumbnail, productIcon, itemView)
//        Glide.with(itemView).load(model.artworkUrl100).placeholder(R.drawable.placeholder).into(songIcon)

        title.text = model.title
        description.text = model.description

    }

}