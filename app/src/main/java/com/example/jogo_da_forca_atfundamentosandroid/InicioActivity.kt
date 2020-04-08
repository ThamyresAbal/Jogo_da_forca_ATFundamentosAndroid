package com.example.jogo_da_forca_atfundamentosandroid

import ViewModel.DadosViewModel
import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.jogo_da_forca_atfundamentosandroid.Model.DadosModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_inicio.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_jogo.*

class InicioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        val model = ViewModelProviders.of(this)[DadosViewModel::class.java]
        val nome = intent.getStringExtra("nome")
        model.dadoUsuario?.nomeJogador = nome

    }
    override fun onBackPressed() {
        // não chame o super desse método
    }

}

