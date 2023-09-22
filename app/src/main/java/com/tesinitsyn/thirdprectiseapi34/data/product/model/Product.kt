package com.tesinitsyn.thirdprectiseapi34.data.product.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String
)
