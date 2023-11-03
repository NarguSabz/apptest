package ca.qc.bdeb.c5gm.exerciceapiweb

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.SocketTimeoutException

class ResultatUniversite : Fragment() {
    val data: RechercheUniversiteViewModel by activityViewModels()

    lateinit var resultat: TextView

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
        return inflater.inflate(R.layout.fragment_resultat_universite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        resultat = view.findViewById(R.id.resultats)
        var universites = data.universites
        data.universites.observe(viewLifecycleOwner) {
            // Mise à jour sur l'écran
            if (universites != null) {
                resultat.text = """
                    ${universites.value?.size} universités correspondent à vos termes de recherche
         La première est : ${if(universites.value?.size != 0) universites.value?.get(0)?.name else ""}
                """.trimIndent()
            }
        }

    }



}