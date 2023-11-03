package ca.qc.bdeb.c5gm.lecteurdemusique.sabbaghziarani

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class ActivityRecycle : AppCompatActivity() {

    lateinit var adapteur: ListeEpicerieAdaptor
    lateinit var recycleView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle)

        recycleView = findViewById(R.id.listeEpicerie)
        val listeEpicerie = listOf(
            ItemEpicerie("Tomates", 10.5, "une tomate"),
            ItemEpicerie("Pommes", 4.0, "une pomme"),
            ItemEpicerie("Oranges", 2.0, "une orange"),
            ItemEpicerie("Tomates", 10.5, "une tomate"),
            ItemEpicerie("Pommes", 4.0, "une pomme"),
            ItemEpicerie("Oranges", 2.0, "une orange"),
            ItemEpicerie("Tomates", 10.5, "une tomate"),
            ItemEpicerie("Pommes", 4.0, "une pomme"),
            ItemEpicerie("Oranges", 2.0, "une orange"), ItemEpicerie("Tomates", 10.5, "une tomate"),
            ItemEpicerie("Pommes", 4.0, "une pomme"),
            ItemEpicerie("Oranges", 2.0, "une orange"), ItemEpicerie("Tomates", 10.5, "une tomate"),
            ItemEpicerie("Pommes", 4.0, "une pomme"),
            ItemEpicerie("Oranges", 2.0, "une orange"),
            ItemEpicerie("Tomates", 10.5, "une tomate"),
            ItemEpicerie("Pommes", 4.0, "une pomme"),
            ItemEpicerie("Oranges", 2.0, "une orange"), ItemEpicerie("Tomates", 10.5, "une tomate"),
            ItemEpicerie("Pommes", 4.0, "une pomme"),
            ItemEpicerie("Oranges", 2.0, "une orange"),
            ItemEpicerie("Tomates", 10.5, "une tomate"),
            ItemEpicerie("Pommes", 4.0, "une pomme"),
            ItemEpicerie("Oranges", 2.0, "une orange")
        )
        adapteur = ListeEpicerieAdaptor(applicationContext, this, listeEpicerie)
        recycleView.adapter = adapteur
    }

    data class ItemEpicerie(val nom: String, val prix: Double, val description: String) {

    }

    class ItemEpicerieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val layout: ConstraintLayout
        val nom: TextView
        val description: TextView
        val prix: TextView

        init {
            layout = itemView as ConstraintLayout
            nom = itemView.findViewById(R.id.nom)
            description = itemView.findViewById(R.id.description)
            prix = itemView.findViewById(R.id.prix)
        }
    }

    class ListeEpicerieAdaptor(
        val ctx: Context,
        val activity: ActivityRecycle,
        var data: List<ItemEpicerie>
    ) : RecyclerView.Adapter<ItemEpicerieHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup, viewType: Int
        ): ItemEpicerieHolder {
            val view =
                LayoutInflater.from(ctx)
                    .inflate(
                        R.layout.liste_epicerie,
                        parent, false
                    )
            return ItemEpicerieHolder(view)
        }

        override fun getItemCount(): Int {
            return data.size
        }

        override fun onBindViewHolder(
            holder: ItemEpicerieHolder, position: Int
        ) {
            val item = data[position]
// On peut ajouter un événement au clic sur cette rangée
            holder.layout.setOnClickListener {
                Toast.makeText(
                    ctx,
                    "On a cliqué sur l'item numéro $position",
                    Toast.LENGTH_SHORT
                ).show()
            }
            holder.nom.text = item.nom
            holder.description.text = item.description
            holder.prix.text = item.prix.toString()
        }

    }


}

