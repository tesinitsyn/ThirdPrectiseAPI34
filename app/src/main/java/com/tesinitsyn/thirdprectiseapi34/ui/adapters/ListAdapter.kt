package com.tesinitsyn.thirdprectiseapi34.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tesinitsyn.thirdprectiseapi34.data.Item
import com.tesinitsyn.thirdprectiseapi34.databinding.ItemBinding

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private lateinit var binding: ItemBinding
    private var itemList = emptyList<Item>()

    class MyViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = itemList[position]
        binding.idTxt.text = currentItem.id.toString()
        binding.nameTxt.text = currentItem.name.toString()
    }

    fun setData(item: List<Item>) {
        this.itemList = item
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

}