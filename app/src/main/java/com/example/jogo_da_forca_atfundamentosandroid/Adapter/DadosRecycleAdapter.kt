package com.example.jogo_da_forca_atfundamentosandroid.Adapter

import ViewModel.Temporaria
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jogo_da_forca_atfundamentosandroid.R
import kotlinx.android.synthetic.main.fragment_resumo.view.*

class DadosRecycleAdapter(
    val dadosRecycleView: List<Temporaria>
) :RecyclerView.Adapter<DadosRecycleAdapter.DadosViewHolder>(){

    class DadosViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val nome : TextView = itemView.textViewNomeJogador
        val palavras : TextView = itemView.textViewAcertos
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DadosViewHolder {

        val v = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.fragment_resumo,
                parent,false
            )
        val dadosViewHolder = DadosViewHolder(v)
        return dadosViewHolder
    }

    override fun getItemCount(): Int = 5// dadosRecycleView.size

    override fun onBindViewHolder(holder: DadosViewHolder, position: Int) {
        val dado = dadosRecycleView[position]
        holder.nome.text = dado.nome
        holder.palavras.text = dado.palavras
    }
}
