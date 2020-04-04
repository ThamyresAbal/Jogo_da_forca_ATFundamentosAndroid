package com.example.jogo_da_forca_atfundamentosandroid.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jogo_da_forca_atfundamentosandroid.Model.DadosModel
import com.example.jogo_da_forca_atfundamentosandroid.R
import kotlinx.android.synthetic.main.resumo_recycle.view.*

class DadosRecycleAdapter(
    val dadosRecycleView: List<DadosModel>
) :RecyclerView.Adapter<DadosRecycleAdapter.DadosViewHolder>(){

    class DadosViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val nome : TextView = itemView.TextViewNomeJogador
        //val palavras : TextView = itemView.TextViewPalavras
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DadosViewHolder {

        val v = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.resumo_recycle,
                parent,false
            )
        val dadosViewHolder = DadosViewHolder(v)
        return dadosViewHolder
    }

    override fun getItemCount(): Int = 1 // dadosRecycleView.size

    override fun onBindViewHolder(holder: DadosViewHolder,position: Int) {
        val dado = dadosRecycleView[position]
        holder.nome.text = dado.nome
        //holder.palavras.text = dado.palavra.get()
    }
}

