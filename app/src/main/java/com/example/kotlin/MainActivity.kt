package com.example.kotlin

import android.app.Activity
import android.app.ProgressDialog
import android.content.ContentValues
import android.content.Intent
import android.content.res.Configuration
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.helpers.AppDatabaseHelper
import com.example.kotlin.models.Car

import androidx.appcompat.widget.SwitchCompat
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentTransaction
import com.example.kotlin.adapters.CarAdapter
import com.example.kotlin.adapters.DetailFragment
import com.example.kotlin.api.ApiCar
import com.example.kotlin.api.ApiSingleton
import com.google.gson.Gson
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

        this.adaptConfigOrientation();
        val carsList = AppDatabaseHelper.getDatabase(this).carsDAO().getCarList()

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

                val cars : MutableList<Car> = ArrayList()

                for(car in data){
                    cars.add(Car(car.id,car.nom,car.prixjournalierbase,car.categorieco2,car.image))
                }

                val carAdapter = CarAdapter(cars, this@MainActivity)
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
                           .getCarList(), this@MainActivity
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

                        val carAdapter = CarAdapter(cars, this@MainActivity)
                        recycler.adapter = carAdapter

                    }

                    override fun onFailure(call: Call<List<ApiCar>>?, t: Throwable?) {
                        progressDialog.dismiss()
                    }
            })
            }
        }

    }

    public fun adaptConfigOrientation() {
        Log.i(ContentValues.TAG, "onCreate: " + resources.configuration.orientation)
        Log.i(ContentValues.TAG, "onConfigurationChanged: ")
        var cont = findViewById<LinearLayout>(R.id.lateral_detail_container)
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            cont.isVisible = true
        } else if (resources.configuration.orientation== Configuration.ORIENTATION_PORTRAIT) {
            cont.isVisible = false
        }
    }

    public fun showDetail(c : Car){
        if(resources.configuration.orientation== Configuration.ORIENTATION_PORTRAIT){
            val intent = Intent(this@MainActivity,DetailActivity::class.java)
            intent.putExtra("id", c.id)
            intent.putExtra("name", c.name)
            intent.putExtra("image", c.image)
            intent.putExtra("co2", c.co2)
            intent.putExtra("price", c.price)
            startActivity(intent)
        } else {
            Log.i(ContentValues.TAG, "PIPOU LE PETIT PINGOUIN EST AFFICHÉ")
            val fragment = DetailFragment()
            val bundle = Bundle()
            bundle.putLong("id", c.id)
            bundle.putString("name", c.name)
            bundle.putString("co2", c.co2)
            bundle.putString("image", c.image)
            bundle.putString("price", c.price)
            bundle.putString("price", c.price)
            fragment.arguments = bundle

            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.lateral_detail, fragment, "exemple2")
            transaction.commit()

        }
    }

}