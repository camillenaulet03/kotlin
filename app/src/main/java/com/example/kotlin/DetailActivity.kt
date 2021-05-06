package com.example.kotlin

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.kotlin.adapters.DetailFragment

class DetailActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_main)

        var id = intent.getLongExtra("id",0)
        var name = intent.getStringExtra("name")
        var image = intent.getStringExtra("image")
        var co2 = intent.getStringExtra("co2")
        var price = intent.getStringExtra("price")

        val fragment = DetailFragment()
        val bundle = Bundle()
        bundle.putLong("id", id)
        bundle.putString("name", name)
        bundle.putString("co2", co2)
        bundle.putString("image", image)
        bundle.putString("price", price)
        fragment.arguments = bundle

        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.lateral_detail, fragment, "exemple2")
        transaction.commit()
    }
}