package com.example.kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.kotlin.adapters.DetailFragment

class DetailActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_main)

        val id = intent.getLongExtra("id",0)
        val name = intent.getStringExtra("name")
        val image = intent.getStringExtra("image")
        val co2 = intent.getStringExtra("co2")
        val price = intent.getStringExtra("price")

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