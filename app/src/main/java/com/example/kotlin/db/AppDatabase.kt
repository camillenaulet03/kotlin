package com.example.kotlin.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kotlin.models.Car

@Database(entities = [Car::class], version = 1)
abstract class AppDatabase : RoomDatabase()
{
    abstract fun countriesDAO(): CarsDTO
}
