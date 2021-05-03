package com.example.myapplication2.add

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication2.R

class AddPlantControl: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_plant_menu)

        val createList = generateAddList(12)

        findViewById<RecyclerView>(R.id.add_list).layoutManager = LinearLayoutManager(this)
        findViewById<RecyclerView>(R.id.add_list).adapter = AddPlantAdapter(createList)

    }

    private fun generateAddList(size: Int ): List<AddPlant> {
        val list = ArrayList<AddPlant>()

        for (i in 0..size) {
            val drawable = when (i % size) {
                0 -> R.drawable.oregano
                1 -> R.drawable.dill
                2 -> R.drawable.parsley
                3 -> R.drawable.chervil
                4 -> R.drawable.thyme
                5 -> R.drawable.sage
                6 -> R.drawable.chives
                7 -> R.drawable.taragon
                8 -> R.drawable.basil
                9 -> R.drawable.cilantro
                10 -> R.drawable.rosemary
                11 -> R.drawable.mint
                else -> R.mipmap.ic_launcher_round
            }
            val name = when (i % size) {
                0 -> "Oregano"
                1 -> "Dill"
                2 -> "Parsley"
                3 -> "Chervil"
                4 -> "Thyme"
                5 -> "Sage"
                6 -> "Chives"
                7 -> "Tarragon"
                8 -> "Basil"
                9 -> "Cilantro"
                10 -> "Rosemary"
                11 -> "Mint"
                else -> "Missing Herb"
            }
            val desc = when (i % size) {
                0 -> "Origanium vulgare"
                1 -> "Anethum vraveolens"
                2 -> "Petroselinum crispum"
                3 -> "Anthiscus Cerefolium"
                4 -> "Thymus vulgaris"
                5 -> "Salvia officinalis"
                6 -> "Allium schoneoprasum"
                7 -> "Artemisia dracunculus"
                8 -> "Ocimum basilicum"
                9 -> "Coriandrum sativum"
                10 -> "Rosemarinus officinalis"
                11 -> "Mentha spp."
                else -> "Missingus herbulis"
            }

            val item = AddPlant(drawable, "$name" , "$desc")
            list += item
        }

        return list
    }
}