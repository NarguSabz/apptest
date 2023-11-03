package ca.qc.bdeb.c5gm.lecteurdemusique.sabbaghziarani

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.Toolbar


class MainActivity : AppCompatActivity() {
    val listeLecture: List<Chanson> = listOf(
        Chanson("Crabe", "Les Goules", R.drawable.crabe),
        Chanson("Driving 55", "Headache24", R.drawable.driving_55),
        Chanson("Flic Romantique", "Joël Martel et les Pépites d'Or", R.drawable.flic_romantique),
        Chanson("Recommencer", "Hubert Lenoir", R.drawable.recommencer),
        Chanson("Sauce Pois", "Alaclair Ensemble", R.drawable.sauce_pois),
    )
    var albumActuel = 0
    lateinit var titre: TextView
    lateinit var artiste: TextView
    lateinit var pochette: ImageView

    val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
    { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data ?: Intent()
            if (intent.hasExtra("titre")) {
                var nom = intent?.getStringExtra("titre").toString()
                Log.d("titre",nom)
                var toast = Toast.makeText(applicationContext, nom, Toast.LENGTH_LONG)
                toast.show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var tool: Toolbar = findViewById(R.id.tool)
        setSupportActionBar(tool)

        titre = findViewById(R.id.titre)
        pochette = findViewById(R.id.pochette)
        artiste = findViewById(R.id.artiste)

        updateView()

    }

    fun updateView() {
        titre.text = "${albumActuel + 1}.${listeLecture[albumActuel].titre}"
        pochette.setImageResource(listeLecture[albumActuel].pochetteResourceId)
        artiste.text = listeLecture[albumActuel].artiste
    }

    fun allerAuPrecedent(view: View) {
        albumActuel--
        if (albumActuel < 0) {
            albumActuel = listeLecture.size - 1
        }
        updateView()
        val intent = Intent(applicationContext, ActivityRecycle::class.java)
        resultLauncher.launch(intent)
    }

    fun pauser(view: View) {
        if ((view as Button).text == "pause") {
            view.text = "jouer"
            titre.text = "${titre.text}(en pause)"
        } else {
            view.text = "pause"
            titre.text = listeLecture[albumActuel].titre
            pochette.alpha = 0.5f
        }
    }

    fun allerAProchaine(view: View) {
        albumActuel++
        if (albumActuel > listeLecture.size - 1) {
            albumActuel = 0
        }

        updateView()
        val intent = Intent(applicationContext, Activity2::class.java)
        intent.putExtra("titre", "s")
        resultLauncher.launch(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.proposApp -> {
                var toast = Toast.makeText(
                    applicationContext,
                    "Ce lecteur de musique a été développé en guise de Labo 1 du cours",
                    Toast.LENGTH_SHORT
                )
                toast.show()
            }

            R.id.proposCours -> {
                var toast = Toast.makeText(
                    applicationContext,
                    "Le cours d’Applications Mobiles est très chouette!",
                    Toast.LENGTH_LONG
                )
                toast.show()
            }

            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    data class Chanson(val titre: String, val artiste: String, val pochetteResourceId: Int) {
    }
}