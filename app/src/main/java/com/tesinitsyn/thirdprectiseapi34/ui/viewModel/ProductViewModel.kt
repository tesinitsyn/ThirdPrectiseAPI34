package com.tesinitsyn.thirdprectiseapi34.ui.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.tesinitsyn.thirdprectiseapi34.data.product.db.ProductDB
import com.tesinitsyn.thirdprectiseapi34.data.product.model.Product
import com.tesinitsyn.thirdprectiseapi34.data.product.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ProductRepository
    val getAllData: LiveData<List<Product>>

    init {
        val productDao = ProductDB.getDatabase(application).productDao()
        repository = ProductRepository(productDao)
        getAllData = repository.getAllData
    }

    fun addItem(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addProduct(product)
        }
    }

    suspend fun addAllItem(products: List<Product>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addAllProduct(products)
        }
    }
}