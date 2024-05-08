package com.example.productscatalogvk.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.productscatalogvk.R
import com.example.productscatalogvk.data.dto.DumResponse
import com.example.productscatalogvk.data.network.DumApi
import com.example.productscatalogvk.data.network.ProductsRepositoryImpl
import com.example.productscatalogvk.data.network.RetrofitNetworkClient
import com.example.productscatalogvk.databinding.ActivityMainBinding
import com.example.productscatalogvk.domain.api.ProductsInteractor
import com.example.productscatalogvk.domain.api.ProductsRepository
import com.example.productscatalogvk.domain.models.Product
import com.example.productscatalogvk.ui.presentation.ProductsSearchViewModel
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var tabMediator: TabLayoutMediator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewPager.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)

        tabMediator = TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when(position) {
                0 -> tab.text ="1"
                1 -> tab.text = "2"

            }
        }
        tabMediator.attach()
    }
    override fun onDestroy() {
        super.onDestroy()
        tabMediator.detach()
    }
}


