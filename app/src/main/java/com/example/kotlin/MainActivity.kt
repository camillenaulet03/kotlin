package com.example.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.helpers.AppDatabaseHelper
import com.example.kotlin.adapters.CarAdapter
import com.example.kotlin.models.Car

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val carsList = AppDatabaseHelper.getDatabase(this).countriesDAO().getCarList()

        // Initialize RecyclerView
        val recycler = findViewById<RecyclerView>(R.id.cars_list)
        recycler.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(this)
        recycler.layoutManager = layoutManager

        val adapter = CarAdapter(carsList)
        recycler.adapter = adapter

        //
        val car = Car(0,"Opel Corsa",2.0,"A","fromage.png")
        AppDatabaseHelper.getDatabase(this).countriesDAO().insert(car)

        println("---- LES VOITURES DU GARAGE ----")
        println(AppDatabaseHelper.getDatabase(this).countriesDAO().getCarList().toString())

    }
}