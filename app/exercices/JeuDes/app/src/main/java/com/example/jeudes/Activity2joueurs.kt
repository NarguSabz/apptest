package com.example.jeudes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

class Activity2joueurs : AppCompatActivity() {
    lateinit var textJoueur1 : TextView
    lateinit var textJoueur2 : TextView

    val des = MainActivity.Des(6)

    lateinit var imageDes : MutableList<ImageView>

    lateinit var joueurs: MutableList<Joueur>
    var joueur = 0



    var tour = 0
    var tours = 10


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activity2joueurs)
        textJoueur1 = findViewById(R.id.joueur1)
        textJoueur2 = findViewById(R.id.joueur2)

        joueurs = mutableListOf()
        joueurs.add(Joueur(intent.getStringExtra("Joueur1"),0,findViewById(R.id.pointsJoueur1) ))
        joueurs.add(Joueur(intent.getStringExtra("Joueur2"),0,findViewById(R.id.pointsJoueur2)))

        textJoueur1.text = intent.getStringExtra("Joueur1")
        textJoueur2.text = intent.getStringExtra("Joueur2")

        imageDes = mutableListOf()
        imageDes.add(findViewById(R.id.imageDes1))
        imageDes.add(findViewById(R.id.imageDes2))
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        imageDes[0].setOnClickListener{rouler(null)}
        imageDes[1].setOnClickListener{rouler(null)}


    }
    fun rouler(view: View?){


        if (tour >= tours ){
            tour = 0
            restartGame()
            return
        }

        var toast = Toast.makeText(applicationContext, "c est le tour de ${joueurs[joueur%2].nom}", Toast.LENGTH_LONG)
        toast.show()
        // var des = Random.nextInt(from = 1, until = 6)
        for(j in 0..1){
            val de= des.rouler()
            var imageDe = when(de){
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                else -> R.drawable.dice_6
            }

            imageDes[j].setImageDrawable(getDrawable(imageDe))
            joueurs[joueur%2].points += de
        }
        joueurs[joueur%2].mettreAJourView()
        if (joueur%2 == 1){
            tour++
        }
        joueur++

    }
    private fun restartGame() {
        joueurs.sortedWith(compareBy { it.points })


        var toast = Toast.makeText(applicationContext, "le gagnant est ${joueurs[0]}", Toast.LENGTH_LONG)
        toast.show()

        for (i in 0 ..1){
            joueurs[i].points = 0
            joueurs[i].mettreAJourView()
        imageDes[0].setImageResource(R.drawable.dice_1)
        imageDes[1].setImageResource(R.drawable.dice_1)
    }}
    class Joueur(val nom: String?, var points: Int,var pointText: TextView){

        fun mettreAJourView(){
            pointText.text = points.toString()
        }
    }
}