package com.example.productscatalogvk.domain.impl

import com.example.productscatalogvk.domain.api.ProductsInteractor
import com.example.productscatalogvk.domain.api.ProductsRepository
import com.example.productscatalogvk.utility.Resource
import java.util.concurrent.Executors

class ProductsInteractorImpl(private val repository: ProductsRepository) : ProductsInteractor {


    private val executor = Executors.newCachedThreadPool()

    override fun searchProducts(position: Int, limit: Int, consumer: ProductsInteractor.TracksConsumer) {
        executor.execute {
            when (val resource = repository.searchProducts(position, limit)) {
                is Resource.Success -> {
                    consumer.consume(resource.data, null)
                }

                is Resource.Error -> {
                    consumer.consume(null, resource.message)
                }
            }
        }
    }
}