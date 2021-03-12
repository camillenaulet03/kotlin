package com.example.kotlin

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.helpers.AppDatabaseHelper
import com.example.kotlin.adapters.CarAdapter
import com.example.kotlin.models.Car

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.api.ApiInterface
import com.example.kotlin.api.ApiSingleton
import com.example.kotlin.api.Car
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    companion object {
        const val PROGRESS_BAR_TITLE = "Récupération des voitures..."
    }

    private var data: ArrayList<Car> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val carsList = AppDatabaseHelper.getDatabase(this).countriesDAO().getCarList()

        // Initialize RecyclerView
        val recycler = findViewById<RecyclerView>(R.id.cars_list)
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

        ApiSingleton.getSingleton.getCars().enqueue(object : Callback<List<Car>> {
            override fun onResponse(call: Call<List<Car>>?, response: Response<List<Car>>?) {
                progressDialog.dismiss()
                data.addAll(response!!.body()!!)
                Log.d("montag", data.toString())
//                adapter.update(data.clone() as ArrayList<Car>)
//                recycler.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<Car>>?, t: Throwable?) {
                progressDialog.dismiss()
            }
        })

    }
}