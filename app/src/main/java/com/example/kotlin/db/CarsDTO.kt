package com.example.kotlin.db

import androidx.room.*
import com.example.kotlin.models.Car

@Dao
abstract class CarsDTO {
    @Query("SELECT * FROM cars")
    abstract fun getCarList(): MutableList<Car>
    @Query("SELECT COUNT(*) FROM cars WHERE id LIKE :id")
    abstract fun checkIfAlreadyExist(id:Long): Int
    @Insert
    abstract fun insert(vararg car: Car)
    @Update
    abstract fun update(vararg car: Car)
    @Delete
    abstract fun delete(vararg car: Car)
}