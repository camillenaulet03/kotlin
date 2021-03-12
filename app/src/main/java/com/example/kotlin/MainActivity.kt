package com.example.kotlin

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.helpers.AppDatabaseHelper
import com.example.kotlin.models.Car

import android.util.Log
import com.example.kotlin.adapters.CarAdapter
import com.example.kotlin.api.ApiCar
import com.example.kotlin.api.ApiSingleton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    companion object {
        const val PROGRESS_BAR_TITLE = "Récupération des voitures..."
    }

    private var data: ArrayList<ApiCar> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val carsList = AppDatabaseHelper.getDatabase(this).countriesDAO().getCarList()

        // Progress Bar
        val progressDialog = ProgressDialog(this)
        progressDialog.setTitle(PROGRESS_BAR_TITLE)
        progressDialog.setCancelable(false)
        progressDialog.show()


        val recycler = findViewById<RecyclerView>(R.id.list_car)
        recycler.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(this)
        recycler.layoutManager = layoutManager


//        val adapter = CarAdapter(data)
//        recycler.adapter = adapter

        ApiSingleton.getSingleton.getCars().enqueue(object : Callback<List<ApiCar>> {
            override fun onResponse(call: Call<List<ApiCar>>?, response: Response<List<ApiCar>>?) {
                progressDialog.dismiss()
                data.addAll(response!!.body()!!)
                Log.d("montag", data.toString())

                val cars : MutableList<Car> = ArrayList()

                for(car in data){
                    cars.add(Car(car.id,car.nom,car.prixjournalierbase,car.categorieco2,car.image))
                }
                println(cars)
                val carAdapter = CarAdapter(cars)
                recycler.adapter = carAdapter

            }

            override fun onFailure(call: Call<List<ApiCar>>?, t: Throwable?) {
                progressDialog.dismiss()
            }
        })

    }
}