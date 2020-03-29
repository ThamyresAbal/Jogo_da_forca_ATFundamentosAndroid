package com.example.jogo_da_forca_atfundamentosandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.jogo_da_forca_atfundamentosandroid.Model.lista
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import android.widget.EditText as EditText1

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      //  var VM = ViewModelProviders.of(this).get(PalavrasViewModel::class.java)

        //exibe um elemento aleatorio da lista
        val texto =  lista.random().toUpperCase()
        textViewTeste.text = texto
        button.setOnClickListener(){
            //Ao clicar no botão o método onClick será executado.
            val letraPorLetra  = editTextLetra.text.toString()
            val tentativas = 5
                if (letraPorLetra.length == 1) {

                    while (tentativas == 0) {
                        if (texto.contains(letraPorLetra)) {
                            textViewPalavraDaVez.text = letraPorLetra

                            var textoDigitado = editTextLetra.text.toString()
                            textViewoioi.text = textoDigitado

                            tentativas - 1
                        } else {
                            Toast.makeText(
                                this,
                                "Errou! Você tem $tentativas",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                } else {
                    Toast.makeText(
                        this,
                        "Apenas uma letra por vez!",
                        Toast.LENGTH_LONG
                    ).show()
            }
        }
    }
}

