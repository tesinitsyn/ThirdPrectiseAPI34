package com.tesinitsyn.thirdprectiseapi34.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tesinitsyn.thirdprectiseapi34.data.product.api.ProductAPI
import com.tesinitsyn.thirdprectiseapi34.data.product.model.Product
import com.tesinitsyn.thirdprectiseapi34.data.product.model.Products
import com.tesinitsyn.thirdprectiseapi34.databinding.FragmentInternetBinding
import com.tesinitsyn.thirdprectiseapi34.ui.adapters.ProductListAdapter
import com.tesinitsyn.thirdprectiseapi34.ui.viewModel.ProductViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InternetFragment : Fragment() {

    private lateinit var binding: FragmentInternetBinding
    private val mProductVM: ProductViewModel by viewModel()
    private lateinit var products: Products

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val retrofit = Retrofit.Builder().baseUrl("https://dummyjson.com")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val productAPI = retrofit.create(ProductAPI::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            products = productAPI.getAllProducts()
            Log.d("idk", products.toString())
            mProductVM.addAllItem(products.products)
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentInternetBinding.inflate(inflater, container, false)

        val adapter = ProductListAdapter()

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        mProductVM.getAllData.observe(viewLifecycleOwner) { product ->
            adapter.setData(product)
        }

        binding.recyclerView.adapter = adapter
        return binding.root
    }
}