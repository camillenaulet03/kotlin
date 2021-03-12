package com.example.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlin.helpers.AppDatabaseHelper
import com.example.kotlin.models.Car

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val car = Car(0,"Opel Corsa",2.0,"A","fromage.png")
        AppDatabaseHelper.getDatabase(this).countriesDAO().insert(car)

        println("---- LES VOITURES DU GARAGE ----")
        println(AppDatabaseHelper.getDatabase(this).countriesDAO().getListeCountries().toString())

    }
}