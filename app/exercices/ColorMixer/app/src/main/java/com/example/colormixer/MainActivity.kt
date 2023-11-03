package com.example.colormixer

import android.graphics.BlendMode
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar


lateinit var toolbar : Toolbar

lateinit var mixture : ImageView

var premierCouleur : ColorFilter? = null
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.tool)
        setSupportActionBar(toolbar)

        mixture = findViewById(R.id.mix)
    }

    fun choisirCouleur(view : View){
        if (premierCouleur == null){
            premierCouleur = view.background.colorFilter
        }else{
           var couleur2 = view.background
            mixture.setImageDrawable(couleur2 - premierCouleur)


        }
    }
}