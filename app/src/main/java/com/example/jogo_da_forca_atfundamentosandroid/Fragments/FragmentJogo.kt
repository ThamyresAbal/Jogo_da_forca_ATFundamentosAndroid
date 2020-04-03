package com.example.jogo_da_forca_atfundamentosandroid.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import ViewModel.DadosViewModel
import androidx.lifecycle.Observer
import com.example.jogo_da_forca_atfundamentosandroid.Model.DadosModel
import com.example.jogo_da_forca_atfundamentosandroid.Model.lista

import com.example.jogo_da_forca_atfundamentosandroid.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_jogo.*

/**
 * A simple [Fragment] subclass.
 */
class FragmentJogo : Fragment() {

    private lateinit var listaPalavrasAcertadas: DadosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jogo, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

        activity?.let {
            listaPalavrasAcertadas = ViewModelProviders.of(it).get(DadosViewModel::class.java)
        }


        Jogo()


    }

    fun Volta(){

        Toast.makeText(activity?.baseContext, "Mizeravi", Toast.LENGTH_LONG).show()
        /*var clicou :Boolean =true
        buttonTentativa.setText( "Jogar denovo")
        while (clicou){
            buttonTentativa.setOnClickListener{
                Jogo()
            }
        }*/
    }

    fun Jogo(){
        var texto =  lista.random().toUpperCase()
     /*   listaPalavrasAcertadas.palavrasUtilizadas.value? = texto
        listaPalavrasAcertadas.palavrasUtilizadas.observe(viewLifecycleOwner, Observer { texto =
            it.toString()
        })*/
        Toast.makeText(activity?.baseContext, "$texto", Toast.LENGTH_LONG).show()
        var tentativas = 5
        var textoOculto = texto
        var caracteresCertos = "" //concatenar cada caracter que estiver certo
        var letrasUsadas = ""

        for (caracter in texto){
            textoOculto = textoOculto.replace(caracter.toString(), "_ ", true)
        }
        textViewPalavraDaVez.text = textoOculto
        buttonTentativa.setOnClickListener() {
            //Ao clicar no botão o método onClick será executado.
            val letraPorLetra = editTextLetra.text.toString().toUpperCase()

            if (letrasUsadas.contains(letraPorLetra)) {
                Toast.makeText(activity?.baseContext, "Você já usou esta letra", Toast.LENGTH_SHORT).show()
            } else {
                letrasUsadas += letraPorLetra
                textViewLetrasUsadas.text = letrasUsadas
                if (tentativas > 0) {
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
                                Toast.makeText(
                                    activity?.baseContext,
                                    "Parabéns!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                textoOculto =
                                    textoOculto.replace(caracter.toString(), "_ ", true)
                            }
                        }
                        if (!textoOculto.contains("_ ")) {
                            Toast.makeText(
                                activity?.baseContext,
                                "Acertou! Vamos a próxima palavra.",
                                Toast.LENGTH_SHORT
                            ).show()

                           Volta()
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
                            activity?.baseContext,
                            "Errou! Você tem $tentativas",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}


