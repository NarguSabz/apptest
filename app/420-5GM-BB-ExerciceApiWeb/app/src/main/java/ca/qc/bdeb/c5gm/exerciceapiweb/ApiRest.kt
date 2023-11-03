/**
 * Exemple simplifié et annoté basé sur cet article : https://www.axopen.com/blog/2021/01/retrofit-projet-android-kotlin/
 *
 * Les données utilisées sont tirées de l'API de test public
 * https://jsonplaceholder.typicode.com/
 *
 * ===================     IMPORTANT     ===================
 * Pour intégrer ce code dans un autre projet, ATTENTION À DEUX CHOSES
 *     1. Les permissions dans le manifest.xml de l'application
 *     2. Les dépendences dans le build.gradle.kts du Module
 * =========================================================
 *
 *
 * Cet exemple utilise la librairie Retrofit pour faire le pont entre
 * un API Rest et un ensemble de classes de données, dans un format
 * très similaire à ce que vous avez déjà fait avec la librairie Room
 * pour gérer votre base de données
 *
 *
 * Dans Room, on avait :
 *
 * - Des Entity, qui étaient nos données à stocker dans la BD
 * - Un DAO (DataAccessObject), une interface qui définissait les requêtes possibles
 * - Un singleton pour accéder à la BD
 *
 * Dans le cas de Retrofit, on a :
 *
 * - Des data class, qui représentent les données que notre API Rest peut retourner ou recevoir en JSON
 * - Une interface qui définit les requêtes possibles
 * - Un singleton pour accéder au gestionnaire de requêtes à l'API
 *
 *
 * Comme dans le cas de la base de données, les opérations faites sur
 * un API sont __potentiellement lourdes__.
 *
 * On va devoir procéder de la même façon, soit en utilisant :
 *
 *         lifecycleScope.launch {
 *             // Une tâche qui va demander d'attendre après des requêtes
 *
 *             val reponse = withContext(Dispatchers.IO) {
 *                 // Faire la requête dans un Thread différent du thread principal
 *                 // question de ne pas bloquer l'interface graphique
 *                 // (souvenez-vous du gif de banane qui danse, on ne veut pas
 *                 // que notre banane arrête de danser le temps que l'accès à Internet
 *                 // se complète!)
 *             }
 *          ...
 */

package ca.qc.bdeb.c5gm.exerciceapiweb

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Ensemble de data class pour représenter les données fournies par l'API
 */

data class Universite(
    val name: String,
    @SerializedName("state-province") val province: String,
    val country: String,
    val domains: List<String>,
    @SerializedName("web_pages") val pageWebs: List<String>,
    @SerializedName("alpha_two_code") val codeDeuxLettre: String,
    )


/**
 * Interface qui spécifie des méthodes qui seront générées automatiquement par la librairie Retrofit
 *
 * Très similaire au DAO (Data Access Object) dans Room où vous définissiez vos requêtes
 *
 * Les méthodes peuvent composer
 */
interface ApiService {
    @GET("search")
    suspend fun getRecherche( @Query("name") name: String): Response<List<Universite>>
}

/**
 * Objet d'accès à l'API : essentiellement équivalent au singleton qui donnait accès à la BD dans le cas de Room
 */
object ApiClient {
    /**
     * URL de base pour toutes les requêtes faites à l'API
     */
    private const val BASE_URL: String = "http://universities.hipolabs.com/"

    /** __by lazy__ est un construit de Kotlin qui permet d'initialiser une variable
     * au moment de l'utiliser pour la première fois
     *
     * Ça fait essentiellement ce qu'un singleton ferait avec :
     *     if(INSTANCE == null)
     *         INSTANCE = new Bidule()
     *
     * Mais en plus gracieux, en utilisant une fonctionnalité du langage plutôt qu'on codant
     * cette logique à la main à chaque fois
     */
    private val gson: Gson by lazy {
        GsonBuilder().setLenient().create()
    }

    private val httpClient: OkHttpClient by lazy {
        OkHttpClient.Builder().build()
    }

    private val retrofit: Retrofit by lazy {
        /**
         *  Le patron Builder est utilisé ici
         *  Voir: https://refactoring.guru/design-patterns/builder
         *
         * Un Builder est un objet de configuration dont les méthodes sont typiquement chaînables
         *
         * C'est une bonne alternative à définir un constructeur avec 2178643 arguments,
         * qui sont tous possiblement optionnels
         */
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
