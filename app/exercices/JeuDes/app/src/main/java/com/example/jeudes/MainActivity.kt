package com.example.jeudes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModel
import java.util.Locale


lateinit var compteurText : TextView
lateinit var imageDes : MutableList<ImageView>
val des = MainActivity.Des(6)
lateinit var toursText : TextView

class MainActivity : AppCompatActivity() {
    val data: InfosANePerdre by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageDes = mutableListOf<ImageView>()
        imageDes.add(findViewById(R.id.imageDes2))
        imageDes.add(findViewById(R.id.imageDes2))
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        imageDes[0].setOnClickListener{rouler(null)}
        imageDes[1].setOnClickListener{rouler(null)}
        compteurText = findViewById(R.id.compteur)
        compteurText.text = data.compteur.toString()

       toursText = findViewById(R.id.textInputEditText)
        toursText.doOnTextChanged { text, start, before, count -> data.tours = text.toString().toInt() }

    }
 /**
    override fun onStart() {
        super.onStart()
        Log.d("start","onstart")
    }

    override fun onPause() {
        super.onPause()
        Log.d("pause","onpaused")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("destroy","ondestroyed")

    }

    override fun onStop() {
        super.onStop()
        Log.d("stop","onstop")
    }

    override fun onResume() {
        super.onResume()
        Log.d("resume","onresumed")

    }

    override fun onRestart() {
        super.onRestart()
        Log.d("restart","onrestart")
    }**/

    fun afficherTours(view: View){
        var toast = Toast.makeText(applicationContext, "Cette partie dure $data.tours tours", Toast.LENGTH_LONG)
        toast.show()
    }
    fun rouler(view: View?){

        if (data.tour >= data.tours ){
            data.tour = 0
            restartGame()
            return
        }
        data.tour++
        for(i in 0..1){
            val de= des.rouler()
            var imageDe = when(de){
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                else -> R.drawable.dice_6
            }

            imageDes[i].setImageDrawable(getDrawable(imageDe))
            data.compteur += de
    }
        compteurText = findViewById(R.id.compteur)
        compteurText.text = data.compteur.toString()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item1 -> restartGame()
            R.id.item2 -> {
                var message = "fait par Narges"
               if (Locale.getDefault().getLanguage().equals("en")){message = "developed by narges"}
                var toast = Toast.makeText(applicationContext,message, Toast.LENGTH_LONG)
                toast.show()            }
            else -> return super.onOptionsItemSelected(item)
        }
        return true}

   /** override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        return super.onPrepareOptionsMenu(menu)

    }**/
   private fun restartGame() {
       if(data.compteur> data.meilleurScore){
           data.meilleurScore = data.compteur

       }
       var toast = Toast.makeText(applicationContext, "votre score final est ${data.compteur.toString()} et votre meilleur score à date est ${data.meilleurScore.toString()}", Toast.LENGTH_LONG)
       toast.show()

       data.compteur = 0
       compteurText = findViewById(R.id.compteur)
       compteurText.text = data.compteur.toString()
       imageDes[0].setImageResource(R.drawable.dice_1)
       imageDes[1].setImageResource(R.drawable.dice_1)
      }
    class Des(private val nombreDes: Int){
        fun  rouler(): Int{
            return (1..nombreDes).random()
        }
    }
    class InfosANePerdre : ViewModel() {
        var meilleurScore = 0
        var compteur = 0
        var tours = 10;
        var tour = 0
    }



}