package ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jogo_da_forca_atfundamentosandroid.Model.DadosModel

class DadosViewModel() : ViewModel() {

    var dadoUsuario: DadosModel? = null

    var palavrasUtilizadas = MutableLiveData<MutableList<DadosModel>>().apply {
        value = mutableListOf() }

}

