package com.example.bottomnavigationpractice.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.bottomnavigationpractice.R

class HomeFragment : Fragment() {
    interface HomeButtonListener {
        fun onClickedListener()
    }

    lateinit var callback: HomeButtonListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        root.findViewById<Button>(R.id.btn_to_notifications).setOnClickListener {
            this.callback.onClickedListener()
        }
        return root
    }
}