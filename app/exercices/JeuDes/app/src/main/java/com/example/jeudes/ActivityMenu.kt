package com.example.jeudes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class ActivityMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }
    fun afficherMenuJeu(view : View){

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)    }

fun afficherMenuNom(view : View){

        val intent = Intent(this, ActivityNoms::class.java)
        startActivity(intent)    }
}