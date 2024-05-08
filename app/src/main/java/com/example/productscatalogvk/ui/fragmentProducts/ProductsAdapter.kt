package com.example.productscatalogvk.ui.fragmentProducts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.productscatalogvk.R
import com.example.productscatalogvk.domain.models.Product

class ProductsAdapter() : RecyclerView.Adapter<ProductsViewHolder>() {

    var productsList = ArrayList<Product>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_card, parent, false)
        return ProductsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.bind(productsList[position])

    }

    override fun getItemCount(): Int {
        return productsList.size
    }
    fun interface LocationClickListener {
        fun onLocationClick(location: Product)
    }
    fun updateTracks(newTracks: List<Product>) {
        productsList.clear()
        productsList.addAll(newTracks)
        notifyDataSetChanged()
    }

}