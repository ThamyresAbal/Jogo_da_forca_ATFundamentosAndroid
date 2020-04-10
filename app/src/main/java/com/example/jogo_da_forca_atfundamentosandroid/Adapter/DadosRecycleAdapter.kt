package com.example.jogo_da_forca_atfundamentosandroid.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jogo_da_forca_atfundamentosandroid.Model.DadosModel
import com.example.jogo_da_forca_atfundamentosandroid.R
import kotlinx.android.synthetic.main.fragment_jogo.view.*
import kotlinx.android.synthetic.main.resumo_recycle.view.*

class DadosRecycleAdapter(
    val dadosRecycleView: MutableList<DadosModel>
) :RecyclerView.Adapter<DadosRecycleAdapter.DadosViewHolder>(){

    class DadosViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val nome : TextView = itemView.TextViewNomeJogador
        val palavras : TextView = itemView.textViewPalavras
        var pontosVogais: TextView = itemView.textViewPontuacaoVogal
        var pontosConsoante: TextView = itemView.textViewPontuacaoConsoante
        var pontosPartida: TextView = itemView.textViewPontuacaoPartida
       // var TotalPontos: TextView = itemView.textViewPontuaçãoTotal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DadosViewHolder {
        val card = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.resumo_recycle, parent, false)
        return DadosViewHolder(card)
    }

    override fun getItemCount(): Int = dadosRecycleView.size

    override fun onBindViewHolder(holder: DadosViewHolder,position: Int) {
        val dado = dadosRecycleView[position]
        holder.nome.text = dado.nomeJogador
        holder.palavras.text = dado.palavrasAcertadas
        holder.pontosVogais.text = dado.pontosVogais
        holder.pontosConsoante.text = dado.pontosConsoante
        holder.pontosPartida.text = dado.pontosPartida
        //holder.TotalPontos.text = dado.totalPontos
    }
}

