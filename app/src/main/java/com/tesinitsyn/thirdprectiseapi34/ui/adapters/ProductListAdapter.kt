package com.tesinitsyn.thirdprectiseapi34.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tesinitsyn.thirdprectiseapi34.data.product.model.Product
import com.tesinitsyn.thirdprectiseapi34.databinding.ProductRowBinding

class ProductListAdapter : RecyclerView.Adapter<ProductListAdapter.MyViewHolder>() {
    private lateinit var binding: ProductRowBinding
    private var productList = emptyList<Product>()

    class MyViewHolder(val binding: ProductRowBinding) : RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun setData(products: List<Product>) {
        this.productList = products
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        binding = ProductRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = productList[position]
        binding.idTxt.text = currentItem.id.toString()
        binding.nameTxt.text = currentItem.title
    }
}