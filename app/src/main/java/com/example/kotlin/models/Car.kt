package com.example.kotlin.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cars")
class Car(
    @PrimaryKey(autoGenerate = true)
    val id: Long  = 0,
    val name: String,
    val price: Double,
    val co2: String,
    val image: String

)
