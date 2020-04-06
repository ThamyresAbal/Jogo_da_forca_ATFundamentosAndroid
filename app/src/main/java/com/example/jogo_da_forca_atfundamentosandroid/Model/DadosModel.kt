package com.example.jogo_da_forca_atfundamentosandroid.Model

class DadosModel(

){

    lateinit var nomeJogador: String
    lateinit var palavrasAcertadas: String

    constructor (palavra: String) : this() {
        this.palavrasAcertadas = palavra
    }

}

