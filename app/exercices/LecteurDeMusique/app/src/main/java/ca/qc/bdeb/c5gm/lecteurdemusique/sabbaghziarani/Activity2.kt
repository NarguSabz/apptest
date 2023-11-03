package ca.qc.bdeb.c5gm.lecteurdemusique.sabbaghziarani

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class Activity2 : AppCompatActivity() {
    lateinit var textt: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        var ing = intent?.extras?.getString("titre")

        var t = Toast.makeText(applicationContext,ing,Toast.LENGTH_SHORT)
        t.show()

        textt= findViewById(R.id.editTextText)
    }

    fun finir(view: View){
        val intentMsg = Intent()
        intentMsg.putExtra("titre", textt.text)
        setResult(RESULT_OK, intentMsg)
        finish()
    }
}