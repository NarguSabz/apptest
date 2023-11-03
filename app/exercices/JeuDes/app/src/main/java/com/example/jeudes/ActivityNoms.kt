package com.example.jeudes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

class ActivityNoms : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noms)
    }
    fun commencerjeu(view: View){
        var joueur1 : TextView = findViewById(R.id.nomJoueur1)
        var joueur2 : TextView = findViewById(R.id.nomJoueur2)
        if(!joueur1.text.isBlank() && !joueur2.text.isBlank()){

        val intent = Intent(this, Activity2joueurs::class.java)
        intent.putExtra("Joueur1",joueur1.text.toString())
        intent.putExtra("Joueur2",joueur2.text.toString())

        startActivity(intent)}
    }
}