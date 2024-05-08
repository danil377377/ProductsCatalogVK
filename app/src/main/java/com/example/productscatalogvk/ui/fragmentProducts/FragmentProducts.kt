package com.example.productscatalogvk.ui.fragmentProducts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.productscatalogvk.databinding.FragmentProductsBinding
import com.example.productscatalogvk.domain.models.Product
import com.example.productscatalogvk.ui.models.ProductsState
import com.example.productscatalogvk.ui.presentation.ProductsSearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentProducts: Fragment() {
    private  val viewModel: ProductsSearchViewModel by viewModel()
    private val productsAdapter = ProductsAdapter()
    companion object {
        private const val POSITION = "position"

        fun newInstance(position: Int) = FragmentProducts().apply {
            arguments = Bundle().apply {
                putInt(POSITION, position)
            }
        }
    }

    private lateinit var binding: FragmentProductsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = productsAdapter
        when(requireArguments().getInt(POSITION)){
            0 -> {
                getProductsList(0)

            }
            1 -> {
                getProductsList(20)

            }
        }


        viewModel.observeState().observe(viewLifecycleOwner) {
            Log.d("Deb", viewModel.products.toString())
            render(it)

        }
    }
    fun getProductsList(position: Int) {
        viewModel.search(position)
    }
    private fun render(state: ProductsState) {
        when (state) {
            is ProductsState.Content -> showContent(state.products)
            is ProductsState.Empty -> showEmpty()
            is ProductsState.Error -> showError()
            is ProductsState.Loading -> showLoading()
        }
    }
    private fun showLoading() {
        binding.recyclerView.setVisibility(View.GONE)
        binding.Empty.setVisibility(View.GONE)
        binding.Error.setVisibility(View.GONE)
        binding.progressBar.visibility = View.VISIBLE
    }
    private fun showError() {
        binding.recyclerView.setVisibility(View.GONE)
        binding.Empty.setVisibility(View.GONE)
        binding.Error.setVisibility(View.VISIBLE)
        binding.progressBar.setVisibility(View.GONE)
    }
    private fun showEmpty() {
        binding.recyclerView.setVisibility(View.GONE)
        binding.Empty.setVisibility(View.VISIBLE)
        binding.Error.setVisibility(View.GONE)
        binding.progressBar.setVisibility(View.GONE)
    }
    private fun showContent(products: List<Product>) {
        binding.recyclerView.setVisibility(View.VISIBLE)
        binding.Empty.setVisibility(View.GONE)
        binding.Error.setVisibility(View.GONE)
        binding.progressBar.setVisibility(View.GONE)
        productsAdapter.productsList.addAll(products)
        productsAdapter.notifyDataSetChanged()
    }


}