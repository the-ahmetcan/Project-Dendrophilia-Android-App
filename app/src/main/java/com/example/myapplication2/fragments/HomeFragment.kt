package com.example.myapplication2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.myapplication2.R


class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_home, container, false)

        val name: String = getArguments()!!.getString("name").toString()
        val detail: String = getArguments()!!.getString("detail").toString()
        val images: Int = getArguments()!!.getInt("image", 0)

        v.findViewById<TextView>(R.id.visualName).text=name
        v.findViewById<TextView>(R.id.visualDesc).text=detail
        v.findViewById<ImageView>(R.id.visual).setImageResource(images)

        return v
    }


}