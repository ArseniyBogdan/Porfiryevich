package com.example.porfiryevich.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.porfiryevich.R

class MainActivity : AppCompatActivity() {
    private lateinit var mainView: ConstraintLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainView = findViewById(R.id.main_activity)
        val mainFragment = MainFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, mainFragment)
            .commit()
    }


}