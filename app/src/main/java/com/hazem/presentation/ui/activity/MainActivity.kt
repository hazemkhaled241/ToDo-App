package com.hazem.presentation.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.hazem.todoapplication.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
   val navHostFragment=supportFragmentManager.findFragmentById(R.id.navHostFragment)as NavHostFragment?
       val navController= navHostFragment!!.navController

    }



}