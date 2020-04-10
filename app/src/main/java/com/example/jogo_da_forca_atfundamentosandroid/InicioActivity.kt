@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.example.jogo_da_forca_atfundamentosandroid

import ViewModel.DadosViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_jogo.*

class InicioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        val model = ViewModelProviders.of(this)[DadosViewModel::class.java]

        val nome = intent.getStringExtra("nome")
        val vogal = intent.getStringExtra("pontosPorconsoantes")
        val consoante = intent.getStringExtra("pontosPorVogais")
        val pontoPartida = intent.getStringExtra("pontosPartida")

        model.dadosJogo.nomeJogador = nome
        model.dadosJogo.pontosVogais = vogal
        model.dadosJogo.pontosConsoante = consoante
        model.dadosJogo.pontosPartida = pontoPartida

        val dadosViewModel = ViewModelProviders.of(this).get(DadosViewModel::class.java)
        dadosViewModel.palavrasUtilizadas.observe( this,
            Observer {
                Log.i("Observe", "PalavrasUtilizadas: ${it}")
            })
    }
    override fun onBackPressed() {
        // não chame o super desse método
    }

}

