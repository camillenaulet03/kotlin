package com.example.kotlin.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiCar(
        @Expose
        @SerializedName("id")
        val id: Long,

        @Expose
        @SerializedName("nom")
        val nom: String,

        @Expose
        @SerializedName("prixjournalierbase")
        val prixjournalierbase: String,

        @Expose
        @SerializedName("categorieco2")
        val categorieco2: String,

        @Expose
        @SerializedName("image")
        val image: String
) {}