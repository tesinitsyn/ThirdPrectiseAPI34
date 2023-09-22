package com.tesinitsyn.thirdprectiseapi34.data.product.repository

import androidx.lifecycle.LiveData
import com.tesinitsyn.thirdprectiseapi34.data.product.dao.ProductDAO
import com.tesinitsyn.thirdprectiseapi34.data.product.model.Product

class ProductRepository(private val productDAO: ProductDAO) {
    val getAllData: LiveData<List<Product>> = productDAO.getAll()

    suspend fun addProduct(product: Product) {
        productDAO.insert(product)
    }

    suspend fun addAllProduct(products: List<Product>) {
        productDAO.insertAll(products)
    }
}