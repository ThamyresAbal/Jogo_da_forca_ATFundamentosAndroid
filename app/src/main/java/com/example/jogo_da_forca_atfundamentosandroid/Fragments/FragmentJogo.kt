package com.example.jogo_da_forca_atfundamentosandroid.Fragments

import ViewModel.DadosViewModel
import android.os.Bundle
import android.view.Gravity
import android.view.Gravity.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.jogo_da_forca_atfundamentosandroid.Model.DadosModel
import com.example.jogo_da_forca_atfundamentosandroid.Model.lista
import com.example.jogo_da_forca_atfundamentosandroid.R
import kotlinx.android.synthetic.main.fragment_jogo.*

/**
 * A simple [Fragment] subclass.
 */
class FragmentJogo : Fragment() {

    private var listaPalavrasAcertadas: DadosViewModel? = null

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
        buttonTesteResumo.setOnClickListener { findNavController().navigate(R.id.fragmentResumo) }
    }
    // PAZ DE ESPÍRITO ^^
    fun ConfigracaoBotao(flag: String) {
        if (flag.contains("tentar")) {
            buttonTentativa.text = "Tentar"
        } else {
            if (flag.contains("continuar")) {
                buttonTentativa.text = "Resumo"
                buttonTentativa.setOnClickListener { findNavController().navigate(R.id.fragmentResumo) }
            } else {
              //  println("mostrar próxima palavra")
                buttonTentativa.text = "Próxima palavra"
                buttonTentativa.setOnClickListener {
                    textViewLetrasUsadas.text = ""
                    Jogo()
                }
            }
        }
    }

    fun RecebeTexto(): String {
        var texto = lista.random().toUpperCase()
        Toast.makeText(activity?.baseContext, "$texto", Toast.LENGTH_LONG).show()
        return texto
    }

    fun SalvarLiveData(texto: String) {
        listaPalavrasAcertadas!!.palavrasUtilizadas.value!!.add(DadosModel("$texto  "))
        listaPalavrasAcertadas!!.palavrasUtilizadas.observe( viewLifecycleOwner,
            Observer { texto })

    }

    fun FormatarTextoSorteado(texto: String): String {
        var textoOculto = texto
        for (caracter in texto) {
            textoOculto = textoOculto.replace(caracter.toString(), "_ ", true)
        }
        textViewPalavraDaVez.text = textoOculto
        return textoOculto
    }

    fun CampoVazio() {
        if (editTextLetra.text.isNullOrBlank()) {
            Toast.makeText(activity?.baseContext, "Ops! Digite algo.", Toast.LENGTH_SHORT)
                .show()
        }
    }


    //Rertonar verdadeiro se a letra já foi usada
    fun LetrasUsadas(letrasUsadas: String, letraPorLetra: String): Boolean {
        return letrasUsadas.contains(letraPorLetra)
    }
    /* ALTEREI AQUI */
    fun VerificaTentativa(tentativas: Int, texto: String): Int {
        var tentativas = tentativas
        tentativas -= 1

        if (tentativas == 0) {
            textViewPalavraDaVez.text = texto
            textViewErro.text = "Fim de jogo!"
            ConfigracaoBotao("continuar")
        } else {
            editTextLetra.text.clear()
            Toast.makeText(
                activity?.baseContext,
                "Errou! Você tem $tentativas",
                Toast.LENGTH_SHORT
            ).show()
        }
        return tentativas
    }

    fun VerificaFimDoJogo(textoOculto: String, texto: String) {
        //Carregando o textoOculto
        textViewPalavraDaVez.text = textoOculto

        //Limpando a caixa de entrada
        editTextLetra.text.clear()

        //verificar se acertou todas as letras
        if (!textoOculto.contains("_ ")) {
            Toast.makeText(
                activity?.baseContext,
                "Acertou!",
                Toast.LENGTH_SHORT
            ).show()
            SalvarLiveData(texto)
            ConfigracaoBotao("x")
        }
    }

    fun Remontagem(caracteresCertos: String, caracter: Char, textoOculto: String): String {
        var textoOculto = textoOculto
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
        return textoOculto
    }

    fun Jogo() {
        var texto = RecebeTexto()
        var textoOculto = FormatarTextoSorteado(texto) //estranho, mas funciona
        var tentativas = 5
        var caracteresCertos = "" //concatenar cada caracter que estiver certo
        var letrasUsadas = ""

        ConfigracaoBotao("tentar")
        buttonTentativa.setOnClickListener {
            val letraPorLetra = editTextLetra.text.toString().toUpperCase()

            CampoVazio()

            if (LetrasUsadas(letrasUsadas, letraPorLetra)) {
                Toast.makeText(
                    activity?.baseContext,
                    "Letra já foi usada!",
                    Toast.LENGTH_SHORT
                ).show()
            }else{
                letrasUsadas += letraPorLetra
                textViewLetrasUsadas.text = letrasUsadas
                if (tentativas > 0) {
                    //Verifica se a letra digita está na palavra
                    if (texto.contains(letraPorLetra)) {
                        caracteresCertos += letraPorLetra

                        textoOculto = texto //Reiniciar a palavra para reiniciar a verificação dos caracteres
                        //Percorrendo verificando quais caracteres acertou
                        for (caracter in texto) {

                            textoOculto = Remontagem(caracteresCertos, caracter, textoOculto)
                        }

                        VerificaFimDoJogo(textoOculto, texto)
                    } else {
                        tentativas = VerificaTentativa(tentativas, texto)
                    }
                }
            }
        }
    }
}
