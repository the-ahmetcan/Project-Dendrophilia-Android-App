package com.example.myapplication2.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication2.MainActivity
import com.example.myapplication2.R
import com.example.myapplication2.dataBase.DataBaseHandler
import com.example.myapplication2.dataBase.DataBaseModel
import com.example.myapplication2.fragments.TabControl


class PlantsAdapter(private val menuList: List<DataBaseModel>) : RecyclerView.Adapter<PlantsAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemPlant: TextView = itemView.findViewById(R.id.plantName)
        val itemDetail: TextView = itemView.findViewById(R.id.plantDetail)
        val itemImages: ImageView = itemView.findViewById(R.id.plantImage)

        init{
            itemView.setOnClickListener{
                v: View -> val position: Int = adapterPosition
                val intent = Intent(itemView.context, TabControl::class.java)
                intent.putExtra("VisualName", menuList[position].savedName)
                intent.putExtra("VisualDetail", menuList[position].savedDesc)
                intent.putExtra("VisualImage", menuList[position].savedImage)
                itemView.context.startActivity(intent)

            }

            itemView.setOnLongClickListener{
                v: View -> val position: Int = adapterPosition
                val dataBaseHandler: DataBaseHandler = DataBaseHandler(v.context)
                val x = DataBaseModel(menuList[position].id, menuList[position].savedName, menuList[position].savedDesc, menuList[position].savedImage)

                val builder = AlertDialog.Builder(v.context)

                builder.setTitle("Delete " + (menuList[position].savedName) + "?")
                builder.setMessage("This is permanent and can't be undone")
                builder.setIcon(R.drawable.ic_baseline_warning_24)

                        .setPositiveButton(R.string.delete_delete) { dialog, which ->
                            Toast.makeText(v.context, menuList[position].savedName + " successfully deleted!", Toast.LENGTH_SHORT).show()

                            dataBaseHandler.deletePlant(x)

                            val intent = Intent(itemView.context, MainActivity::class.java)
                            itemView.context.startActivity(intent)
                        }

                        .setNegativeButton(R.string.delete_cancel) { dialog, which ->
                        }

                builder.show()
                true

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.plant_cards, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = menuList[position]

        holder.itemPlant.text = currentItem.savedName
        holder.itemDetail.text = currentItem.savedDesc
        holder.itemImages.setImageResource(currentItem.savedImage)

    }
}