package com.example.kotlin.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Car(
        @Expose
        @SerializedName("nom")
        val name: String,

        @Expose
        @SerializedName("prixjournalierbase")
        val capital: String,

        @Expose
        @SerializedName("categorieco2")
        val region: String,

        @Expose
        @SerializedName("image")
        val image: String
) {}