package com.tesinitsyn.thirdprectiseapi34.ui.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.tesinitsyn.thirdprectiseapi34.data.Item
import com.tesinitsyn.thirdprectiseapi34.data.ItemDB
import com.tesinitsyn.thirdprectiseapi34.data.ItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ItemRepository
    val getAllData: LiveData<List<Item>>

    init {
        val itemDAO = ItemDB.getDatabase(application).itemDAO()
        repository = ItemRepository(itemDAO)
        getAllData = repository.getAllDAO
    }

    fun addItem(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addItem(item)
        }
    }

}