package com.example.myapplication2.add

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication2.MainActivity
import com.example.myapplication2.R

class AddPlantAdapter(private val plantList: List<AddPlant>): RecyclerView.Adapter<AddPlantAdapter.AddPlantViewHolder>() {

     inner class AddPlantViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
         val visualImage: ImageView = itemView.findViewById(R.id.add_visualImage)
         val visualName: TextView = itemView.findViewById(R.id.add_visualName)
         val visualDesc: TextView = itemView.findViewById(R.id.add_visualDesc)

         init {
             itemView.setOnClickListener {
                 val position = adapterPosition
                 val intent = Intent(itemView.context, MainActivity::class.java)
                 intent.putExtra("VisualName", plantList[position].visualNameResource)
                 intent.putExtra("VisualDetail", plantList[position].visualDescResource)
                 intent.putExtra("VisualImage", plantList[position].visualImageResource)
                 Toast.makeText(itemView.context, "You clicked on item #${position + 1}", Toast.LENGTH_SHORT).show()
                 itemView.context.startActivity(intent)

             }
         }
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddPlantViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.add_cards, parent, false)
        return AddPlantViewHolder(v)

    }

    override fun getItemCount() = plantList.size

    override fun onBindViewHolder(holder: AddPlantViewHolder, position: Int) {
        val currentItem = plantList[position]

        holder.visualImage.setImageResource(currentItem.visualImageResource)
        holder.visualName.text = currentItem.visualNameResource
        holder.visualDesc.text = currentItem.visualDescResource

    }
}