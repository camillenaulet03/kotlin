package com.example.kotlin.db

import androidx.room.*
import com.example.kotlin.models.Car

@Dao
abstract class CarsDTO {
    @Query("SELECT * FROM cars")
    abstract fun getListeCountries(): MutableList<Car>
    @Insert
    abstract fun insert(vararg country: Car)
    @Update
    abstract fun update(vararg country: Car)
    @Delete
    abstract fun delete(vararg country: Car)
}