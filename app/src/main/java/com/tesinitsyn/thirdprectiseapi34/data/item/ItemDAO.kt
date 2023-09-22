package com.tesinitsyn.thirdprectiseapi34.data.item


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tesinitsyn.thirdprectiseapi34.data.item.Item

@Dao
interface ItemDAO {

    @Query("SELECT * FROM item")
    fun getAll(): LiveData<List<Item>>

    @Insert(onConflict = OnConflictStrategy.NONE)
    suspend fun insert(item: Item)

}