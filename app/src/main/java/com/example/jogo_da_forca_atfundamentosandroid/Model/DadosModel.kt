package com.example.jogo_da_forca_atfundamentosandroid.Model

class DadosModel(){

    var nomeJogador: String = ""
    var palavrasAcertadas: String = ""

    constructor (nome: String, palavra: String) : this() {
        this.nomeJogador = nome
        this.palavrasAcertadas = palavra
    }

//    fun addNovaPalavra(palavra: String){
//        this.palavrasAcertadas += palavra + ";"
//    }

    override fun toString(): String {
        return "${nomeJogador}: ${palavrasAcertadas}"
    }
}

