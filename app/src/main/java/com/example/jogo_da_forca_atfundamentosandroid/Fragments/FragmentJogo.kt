package com.example.jogo_da_forca_atfundamentosandroid.Fragments

import ViewModel.DadosViewModel
import android.os.Bundle
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

    private var dadosViewModel: DadosViewModel? = null

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
            dadosViewModel = ViewModelProviders.of(it).get(DadosViewModel::class.java)
        }
        Jogo()

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
    fun ImagemForca(tentativas: Int){

        return when{
            tentativas == 4 ->imageViewForca.setImageResource(R.drawable.icone_hangman_1);
            tentativas == 3 ->imageViewForca.setImageResource(R.drawable.icone_hangman_2);
            tentativas == 2 ->imageViewForca.setImageResource(R.drawable.icone_hangman_3);
            tentativas == 1 ->imageViewForca.setImageResource(R.drawable.icone_hangman_4);
            else ->imageViewForca.setImageResource(R.drawable.icone_hangman_5);
        }
    }

    fun RecebeTexto(): String {
        var texto = lista.random().toUpperCase()
        Toast.makeText(activity?.baseContext, "$texto", Toast.LENGTH_LONG).show()
        return texto
    }

    fun SalvarLiveData(texto: String) {
        var listaAtualizada = dadosViewModel!!.palavrasUtilizadas.value!!

        // Abordagem atual
        listaAtualizada.add(DadosModel(dadosViewModel?.dadoUsuario!!.nomeJogador, "$texto  "))

        // Outra abordagem
        dadosViewModel?.dadoUsuario!!.palavrasAcertadas += texto

        dadosViewModel!!.palavrasUtilizadas.value = listaAtualizada //.add(DadosModel("$texto  "))
        dadosViewModel!!.palavrasUtilizadas.observe( viewLifecycleOwner,
        Observer {
            texto
        })

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
    fun PontosPorPalavra():Boolean{
        return true
    }
    fun PontuacaoFinal(vogais:Int, consoantes: Int){
        val pontoPalavra = 10
        var pontoFinal = consoantes + vogais
        if (!PontosPorPalavra()){
            pontoFinal + pontoPalavra
            textViewPontuaçãoTotal.text = pontoFinal.toString()
        }else{
            textViewPontuaçãoTotal.text = pontoFinal.toString()
        }
    }

    fun ConsoantesVogais(letraPorLetra: String){
        val vogais = "aeiou"
        var consoantePontos = 0
        var vogaisPontos = 0
        if(vogais.toUpperCase().contains(letraPorLetra.toUpperCase())){
            vogaisPontos += vogaisPontos + 2
            textViewPontuaçãoVogal.text = vogaisPontos.toString()
        }else{
            consoantePontos += consoantePontos + 1
            textViewPontuaçãoConsoante.text = consoantePontos.toString()
        }
        PontuacaoFinal(vogaisPontos, consoantePontos)
    }

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

    fun VerificaFimJogo(textoOculto: String, texto: String) {
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
            PontosPorPalavra()
            SalvarLiveData(texto)
            ConfigracaoBotao("x")
        }
    }

    fun VerificaAcertoLetra(caracteresCertos: String, caracter: Char, textoOculto: String): String {
        var textoOculto = textoOculto
        //remontando a palavra do jogo mostrando os caracteres certos e ocultando
        //aqueles que ainda não acertaram
        if (caracteresCertos.contains(caracter.toString())) {
            //Verificar para mostrar somente as letras certas
            //acertou a letra
            textoOculto = textoOculto.replace(
                caracter.toString(),
                caracter.toString(),
                true
            )
//            Toast.makeText(
//                activity?.baseContext,
//                "Parabéns!",
//                Toast.LENGTH_SHORT
//            ).show()
            ConsoantesVogais(caracter.toString())
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
        var letrasErradas = ""

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
                            textoOculto = VerificaAcertoLetra(caracteresCertos, caracter, textoOculto)
                        }
                        VerificaFimJogo(textoOculto, texto)
                    } else {
                        letrasErradas += letraPorLetra
                        textViewLetrasErradas.text = letrasErradas
                        tentativas = VerificaTentativa(tentativas, texto)
                        ImagemForca(tentativas)
                    }
                }
            }
        }
    }
}
