package com.example.kotlin.adapters

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.kotlin.R
import com.example.kotlin.helpers.AppDatabaseHelper
import com.example.kotlin.models.Car
import com.squareup.picasso.Picasso

class DetailFragment : Fragment()
{
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {

        val arguments = requireArguments()

        val viewDetail = inflater.inflate(R.layout.item_detail, container, false)
        viewDetail.findViewById<TextView>(R.id.name).text = arguments.getString("name")
        viewDetail.findViewById<TextView>(R.id.category).text = ("Catégorie CO2 : ") + arguments.getString("co2")
        viewDetail.findViewById<TextView>(R.id.price).text = arguments.getString("price") + ("€ / jour")
        Picasso.get().load("http://s519716619.onlinehome.fr/exchange/madrental/images/"+arguments.getString("image")).into(viewDetail.findViewById<ImageView>(R.id.image))

        var car = Car(arguments.getLong("id"),arguments.getString("name")!!,arguments.getString("price")!!,arguments.getString("co2")!!,arguments.getString("image")!!)
        viewDetail.findViewById<Button>(R.id.favoriteButton).setOnClickListener {
            Log.i(ContentValues.TAG, "PIPOU LE PETIT PINGOUIN EST FAVORISÉ")
            if( AppDatabaseHelper.getDatabase(this.context!!).carsDAO().checkIfAlreadyExist(car.id) == 0 ){
                AppDatabaseHelper.getDatabase(this.context!!).carsDAO().insert(car)
            }
        }
        return viewDetail
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)
        val arguments = requireArguments()
        Log.i(TAG, "PINGU  " + arguments.toString())
    }
}