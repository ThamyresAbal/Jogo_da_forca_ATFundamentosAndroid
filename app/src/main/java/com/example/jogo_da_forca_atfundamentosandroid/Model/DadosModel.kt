package com.example.jogo_da_forca_atfundamentosandroid.Model

class DadosModel(){

    var nomeJogador: String = ""
    var palavrasAcertadas: String = ""

    constructor (palavra: String) : this() {
        this.palavrasAcertadas = palavra
    }

}

