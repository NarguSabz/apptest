package ca.qc.bdeb.c5gm.exerciceapiweb

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.SocketTimeoutException

class RechercheUniversite : Fragment() {
    lateinit var nomARechercher: EditText
    lateinit var bouton: Button
    val data: RechercheUniversiteViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recherche_universite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nomARechercher = view.findViewById(R.id.NomUni)
        bouton = view.findViewById<Button?>(R.id.button)
        bouton.setOnClickListener {
            afficherUniversites()
        }
    }

    fun afficherUniversites() {

        lifecycleScope.launch {
            try {
                val reponse = withContext(Dispatchers.IO) {
                    ApiClient.apiService.getRecherche(nomARechercher.text.toString())
                }
                if (!reponse.isSuccessful || reponse.body() == null) {
                    Toast.makeText(
                        requireContext(),
                        "Erreur: impossible de se connecter à l'API",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@launch
                }
                data.universites.value = reponse.body()!!

            } catch (e: SocketTimeoutException) {
                Toast.makeText(
                    requireContext(),
                    "Erreur: Réseau indisponible",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }
}