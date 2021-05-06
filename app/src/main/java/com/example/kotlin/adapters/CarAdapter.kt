package com.example.kotlin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.R
import com.example.kotlin.models.Car
import com.squareup.picasso.Picasso

class CarAdapter(private var carList: MutableList<Car>) : RecyclerView.Adapter<CarAdapter.CarViewHolder>()
{
    var onItemClick: ((Car) -> Unit)? = null
    // Crée chaque vue item à afficher :
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder      {
        val viewCourse = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return CarViewHolder(viewCourse)
    }
// Renseigne le contenu de chaque vue item :
    override fun onBindViewHolder(holder: CarViewHolder, position: Int)      {

    holder.textViewCarName .text = carList[position].name
    holder.textViewCarPrice.text = carList[position].price + ("€ / jour")
    holder.textViewCarCO2.text = ("Catégorie CO2 : ") + carList[position].co2

    Picasso.get().load("http://s519716619.onlinehome.fr/exchange/madrental/images/"+carList[position].image).fit().centerInside().into(holder.imageViewCarPicture)
    }

    override fun getItemCount(): Int = carList.size

// ViewHolder :
    inner class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)      {
        val textViewCarName: TextView = itemView.findViewById(R.id.name)
        val textViewCarPrice: TextView = itemView.findViewById(R.id.price)
        val textViewCarCO2: TextView = itemView.findViewById(R.id.category)
        val imageViewCarPicture: ImageView = itemView.findViewById(R.id.image)

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(carList[adapterPosition])
            }
        }
    }


}