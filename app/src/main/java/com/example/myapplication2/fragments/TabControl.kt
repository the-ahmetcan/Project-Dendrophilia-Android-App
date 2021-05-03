package com.example.myapplication2.fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication2.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class TabControl: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_tabs)

        val homeFragment = HomeFragment()
        val lightsFragment = LightsFragment()

        val name: String = intent.getStringExtra("VisualName").toString()
        val detail: String = intent.getStringExtra("VisualDetail").toString()
        val images: Int = intent.getIntExtra("VisualImage", 0)

        val bundle = Bundle()
        bundle.putString("name",name)
        bundle.putString("detail",detail)
        bundle.putInt("image",images)
        homeFragment.setArguments(bundle)

        makeCurrentFragment(homeFragment)
        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_home -> makeCurrentFragment(homeFragment)
                R.id.ic_lights -> makeCurrentFragment(lightsFragment)
            }
            true
        }
    }
    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply{
            replace(R.id.fragment_container, fragment)
            commit()
    }

}