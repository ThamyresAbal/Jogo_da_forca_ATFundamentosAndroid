package ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jogo_da_forca_atfundamentosandroid.Model.DadosModel
import com.example.jogo_da_forca_atfundamentosandroid.Model.Pontuacao

class DadosViewModel() : ViewModel() {

    var dadosJogo = DadosModel()

    var palavrasUtilizadas= MutableLiveData<MutableList<DadosModel>>().apply {
        value = mutableListOf() }



}

