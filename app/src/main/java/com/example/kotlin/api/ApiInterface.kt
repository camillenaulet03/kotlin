package com.example.kotlin.api

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    // appel get :
    @GET("exchange/madrental/get-vehicules.php")
    fun getCars(): Call<List<Car>>
}