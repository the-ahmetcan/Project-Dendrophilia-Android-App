package com.example.myapplication2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication2.dataBase.DataBaseHandler
import com.example.myapplication2.dataBase.DataBaseModel
import com.example.myapplication2.add.AddPlantControl
import com.example.myapplication2.home.PlantsAdapter


class MainActivity : AppCompatActivity() {
    private var nameList = ArrayList<String>()
    private var descList = ArrayList<String>()
    private var imagesList = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this, "main", Toast.LENGTH_SHORT).show()

        addPlantToList()
        setupPlants()

        findViewById<ImageButton>(R.id.add_button).setOnClickListener{
            val intent = Intent(this, AddPlantControl::class.java)



            startActivity(intent)
        }
    }

    private fun addPlantToList() {
        val name: String = intent.getStringExtra("VisualName").toString()
        val detail: String = intent.getStringExtra("VisualDetail").toString()
        val images: Int = intent.getIntExtra("VisualImage", 0)

        val databaseHandler: DataBaseHandler = DataBaseHandler(this)

        if(name != "null" && detail != "null" && images != null) {
            Toast.makeText(applicationContext, "Beep", Toast.LENGTH_SHORT).show()
            val status =
                    databaseHandler.addPlant(DataBaseModel(0, name, detail, images))
            if(status > -1){
                Toast.makeText(applicationContext, "Plant added", Toast.LENGTH_SHORT).show()

                setupPlants()
            }
        } else {
            Toast.makeText(applicationContext, "ERROR 1", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupPlants() {

        if(getItemsList().size > 0) {

            Toast.makeText(applicationContext, "setup plants true", Toast.LENGTH_SHORT).show()
            findViewById<RecyclerView>(R.id.recyclerView).visibility = View.VISIBLE
            findViewById<RecyclerView>(R.id.recyclerView).layoutManager = LinearLayoutManager(
                    this, LinearLayoutManager.HORIZONTAL, false)

            val plantAdapter = PlantsAdapter(getItemsList())

            findViewById<RecyclerView>(R.id.recyclerView).adapter = plantAdapter

        }else {

            findViewById<RecyclerView>(R.id.recyclerView).visibility = View.GONE
            Toast.makeText(applicationContext, "ERROR 2", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getItemsList(): ArrayList<DataBaseModel> {
        val dataBaseHandler: DataBaseHandler = DataBaseHandler(this)
        return dataBaseHandler.viewPlant()
    }

//    private fun createPlantList() {
//        val bundle: Bundle? = intent.extras
//        val visualName: String = intent.getStringExtra("VisualName").toString()
//        val visualDesc: String = intent.getStringExtra("VisualDetail").toString()
//        val visualImage: Int = intent.getIntExtra("VisualImage", 0)
//
//        if(visualName != "null"){
//            addPlantToList(visualName, visualDesc, visualImage)
//        }
//    }


}