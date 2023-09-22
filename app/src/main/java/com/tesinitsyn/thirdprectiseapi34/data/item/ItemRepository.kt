package com.tesinitsyn.thirdprectiseapi34.data.item

import androidx.lifecycle.LiveData
import com.tesinitsyn.thirdprectiseapi34.data.item.Item
import com.tesinitsyn.thirdprectiseapi34.data.item.ItemDAO

class ItemRepository(private val itemDAO: ItemDAO) {

    var getAllDAO: LiveData<List<Item>> = itemDAO.getAll()

    suspend fun addItem(item: Item) {
        itemDAO.insert(item)
    }
}