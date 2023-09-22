package com.tesinitsyn.thirdprectiseapi34.data.product.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tesinitsyn.thirdprectiseapi34.data.product.dao.ProductDAO
import com.tesinitsyn.thirdprectiseapi34.data.product.model.Product

@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class ProductDB : RoomDatabase() {

    abstract fun productDao(): ProductDAO

    companion object {
        @Volatile
        private var INSTANCE: ProductDB? = null

        fun getDatabase(context: Context): ProductDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductDB::class.java,
                    "product_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }

}