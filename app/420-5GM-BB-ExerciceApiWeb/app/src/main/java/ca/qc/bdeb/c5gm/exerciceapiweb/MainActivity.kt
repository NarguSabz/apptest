package ca.qc.bdeb.c5gm.exerciceapiweb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.SocketTimeoutException

class MainActivity : AppCompatActivity() {

    lateinit var nomARechercher: EditText
    lateinit var resultat: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nomARechercher = findViewById(R.id.NomUni)
        resultat = findViewById(R.id.resultats)
        /* nomARechercher.addTextChangedListener(object : TextWatcher {
             override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
             }

             override fun afterTextChanged(s: Editable?) {
             }

             override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                 afficherUniversites(s.toString())

             }
         })*/

    }

    fun afficherUniversites(view: View) {

        lifecycleScope.launch {
            try {
                val reponse = withContext(Dispatchers.IO) {
                    ApiClient.apiService.getRecherche(nomARechercher.text.toString())
                }
                if (!reponse.isSuccessful || reponse.body() == null) {
                    Toast.makeText(
                        applicationContext,
                        "Erreur: impossible de se connecter à l'API",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@launch
                }
                val universites = reponse.body()!!
                resultat.text = """
                ${universites.size} universités correspondent à vos termes de recherche
     La première est : ${if(universites.size != 0) universites[0].name else ""}
            """.trimIndent()
            } catch (e: SocketTimeoutException) {
                Toast.makeText(
                    applicationContext,
                    "Erreur: Réseau indisponible",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }
}
