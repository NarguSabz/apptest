package com.example.toasternsz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar


class MainActivity : AppCompatActivity() {
    lateinit var imageToast: ImageView
    var puissance = 1
    lateinit var puissanceText: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        imageToast = findViewById(R.id.imageToast)
        puissanceText = findViewById(R.id.puissance)
    }

    fun ajouterPuissance(view: View) {
        puissance++
        if (puissance > 3) {
            puissance = 3
        }
        puissanceText.text = puissance.toString()
    }

    fun diminuerPuissance(view: View) {
        puissance--
        if (puissance < 1) {
            puissance = 1
        }
        puissanceText.text = puissance.toString()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.ramasser -> recommencer()
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    fun recommencer() {
        imageToast.setImageResource(R.drawable.toaster_main)
    }

   fun demarrerCounter( view : View){
       recommencer()
    object : CountDownTimer(5000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            // ne rien faire
        }

        override fun onFinish() {
            //afficher le toast + changer l'image en fonction du réglage du toaster

            val toast = Toast.makeText(applicationContext, "les toasts sont prêts", Toast.LENGTH_LONG)
            toast.show()

            when(puissance){
            1 -> imageToast.setImageResource(R.drawable.toast_1)
            2 -> imageToast.setImageResource(R.drawable.toast_2)
            3 -> imageToast.setImageResource(R.drawable.toast_3)
            }
        }
    }.start()
    }
}