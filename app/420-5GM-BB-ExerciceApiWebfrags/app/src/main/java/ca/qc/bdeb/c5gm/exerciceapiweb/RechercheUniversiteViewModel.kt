package ca.qc.bdeb.c5gm.exerciceapiweb

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RechercheUniversiteViewModel : ViewModel() {
        var universites :MutableLiveData<List<Universite>> = MutableLiveData()
}