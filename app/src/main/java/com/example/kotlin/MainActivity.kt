package com.example.kotlin

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.models.Car

import androidx.appcompat.widget.SwitchCompat
import com.example.kotlin.adapters.CarAdapter
import com.example.kotlin.api.ApiCar
import com.example.kotlin.api.ApiSingleton
import com.example.kotlin.helpers.AppDatabaseHelper
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

        // Progress Bar
        val progressDialog = ProgressDialog(this)
        progressDialog.setTitle(PROGRESS_BAR_TITLE)
        progressDialog.setCancelable(false)
        progressDialog.show()


        val recycler = findViewById<RecyclerView>(R.id.list_car)
        recycler.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(this)
        recycler.layoutManager = layoutManager

        ApiSingleton.getSingleton.getCars().enqueue(object : Callback<List<ApiCar>> {
            override fun onResponse(call: Call<List<ApiCar>>?, response: Response<List<ApiCar>>?) {
                progressDialog.dismiss()
                data.addAll(response!!.body()!!)

                val cars : MutableList<Car> = ArrayList()

                for(car in data){
                    cars.add(Car(car.id,car.nom,car.prixjournalierbase,car.categorieco2,car.image))
                }
                val carAdapter = CarAdapter(cars)
                recycler.adapter = carAdapter

            }

            override fun onFailure(call: Call<List<ApiCar>>?, t: Throwable?) {
                progressDialog.dismiss()
            }
        })

        val switch = findViewById<SwitchCompat>(R.id.switch_button)
        switch?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
               val adapter = CarAdapter(
                       AppDatabaseHelper
                           .getDatabase(this)
                           .carsDAO()
                           .getCarList()
               )
                recycler.adapter = adapter
            } else {
                ApiSingleton.getSingleton.getCars().enqueue(object : Callback<List<ApiCar>> {
                    override fun onResponse(call: Call<List<ApiCar>>?, response: Response<List<ApiCar>>?) {
                        progressDialog.dismiss()

                        val cars : MutableList<Car> = ArrayList()

                        for(car in data){
                            cars.add(Car(car.id,car.nom,car.prixjournalierbase,car.categorieco2,car.image))
                        }

                        val carAdapter = CarAdapter(cars)
                        recycler.adapter = carAdapter

                    }

                    override fun onFailure(call: Call<List<ApiCar>>?, t: Throwable?) {
                        progressDialog.dismiss()
                    }
                })
            }
        }

    }
}