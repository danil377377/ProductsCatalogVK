package com.example.productscatalogvk.ui.presentation

import android.app.Application
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.productscatalogvk.domain.api.ProductsInteractor
import com.example.productscatalogvk.domain.models.Product
import com.example.productscatalogvk.ui.models.ProductsState

class ProductsSearchViewModel (application: Application,
                               val productsInteractor: ProductsInteractor
): AndroidViewModel(application)
{
        companion object {
            private const val LIMIT = 20

        }
         val products = ArrayList<Product>()
        private val handler = Handler(Looper.getMainLooper())
        private var searchPosition: Int = -1
        private val searchRunnable = Runnable {
            searchRequest(searchPosition)
        }


        private val stateLiveData = MutableLiveData<ProductsState>()
        fun observeState(): LiveData<ProductsState> = stateLiveData


        override fun onCleared() {
            handler.removeCallbacksAndMessages(searchRunnable)
        }
        fun search(newSearchPosition: Int) {
            this.searchPosition= newSearchPosition
            handler.removeCallbacks(searchRunnable)
            handler.post(searchRunnable)
        }

        fun searchRequest(searchPosition: Int) {
            if (searchPosition != -1) {
                renderState(
                    ProductsState.Loading
                )
                productsInteractor.searchProducts(searchPosition, LIMIT, object : ProductsInteractor.TracksConsumer {
                    override fun consume(foundProducts: List<Product>?, errorMessage: String?) {
                        handler.post {
                            if (foundProducts != null) {
                                products.addAll(foundProducts)
                            }
                            when {
                                errorMessage != null -> {
                                    renderState(
                                        ProductsState.Error()
                                    )
                                }
                                products.isEmpty() -> {
                                    renderState(
                                        ProductsState.Empty()
                                    )
                                }
                                else -> {
                                    renderState(
                                        ProductsState.Content(
                                            products = products
                                        )
                                    )
                                }
                            }

                        }
                    }
                })
            }
        }
        private fun renderState(state: ProductsState) {
            stateLiveData.postValue(state)
        }
    }
