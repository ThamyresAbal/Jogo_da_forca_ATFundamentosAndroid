package com.example.jogo_da_forca_atfundamentosandroid

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.jogo_da_forca_atfundamentosandroid.Model.lista
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

     lateinit var listaPalavrasAcertadas: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //var VM = ViewModelProviders.of(this).get(PalavrasViewModel::class.java)

        //falta ver lance o enter
        //falta ver proxima palavra

        //exibe um elemento aleatorio da lista
        var texto =  lista.random().toUpperCase()
        var tentativas = 5
        var textoOculto = texto
        var caracteresCertos = "" //concatenar cada caracter que estiver certo
        var letrasUsadas = ""

        for (caracter in texto){
            textoOculto = textoOculto.replace(caracter.toString(), "_ ", true)
        }
        textViewPalavraDaVez.text = textoOculto


        button.setOnClickListener() {
            //Ao clicar no botão o método onClick será executado.
            val letraPorLetra = editTextLetra.text.toString().toUpperCase()

            if (letrasUsadas.contains(letraPorLetra)) {
                Toast.makeText(this,"Você já usou esta letra", Toast.LENGTH_SHORT).show()
            }else {
                letrasUsadas += letraPorLetra
                textViewLetrasUsadas.text = letrasUsadas
                if (tentativas > 0) {
                    if (letraPorLetra.length == 1) {
                        if (texto.contains(letraPorLetra)) {
                            caracteresCertos += letraPorLetra
                            textoOculto =
                                texto //Reiniciar a palavra para reiniciar a verificação dos caracteres
                            //Percorrendo verificando quais caracteres acertou
                            for (caracter in texto) {
                                //remontando a palavra do jogo mostrando os caracteres certos e ocultando
                                //aqueles que ainda não acertaram
                                if (caracteresCertos.contains(caracter.toString(), true)) {
                                    //Verificar para mostrar somente as letras certas
                                    //acertou a letra
                                    textoOculto = textoOculto.replace(
                                        caracter.toString(),
                                        caracter.toString(),
                                        true
                                    )
                                    Toast.makeText(this, "Parabéns!", Toast.LENGTH_SHORT).show()
                                } else {
                                    //errou
                                    //Ocultar os outros caracteres
                                    textoOculto =
                                        textoOculto.replace(caracter.toString(), "_ ", true)
                                }
                            }
                            if (!textoOculto.contains("_")) {
                                Toast.makeText(
                                    this,
                                    "Acertou! Vamos a próxima palavra.",
                                    Toast.LENGTH_SHORT
                                ).show()
                                listaPalavrasAcertadas.add(texto)
                                 texto = lista.random().toUpperCase()
                            }
                            textViewPalavraDaVez.text = textoOculto
                            editTextLetra.text.clear()

                        } else {
                            tentativas -= 1
                            if (tentativas == 0) {
                                textViewPalavraDaVez.text = texto
                                textViewErro.text = "Fim de jogo!"
                            }
                            editTextLetra.text.clear()
                            Toast.makeText(
                                this,
                                "Errou! Você tem $tentativas",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            this,
                            "Apenas uma letra por vez!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}

