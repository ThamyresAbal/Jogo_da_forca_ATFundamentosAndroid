package com.example.jogo_da_forca_atfundamentosandroid.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import ViewModel.DadosViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jogo_da_forca_atfundamentosandroid.Adapter.DadosRecycleAdapter

import com.example.jogo_da_forca_atfundamentosandroid.R
import kotlinx.android.synthetic.main.fragment_resumo.*

/**
 * A simple [Fragment] subclass.
 */
class FragmentResumo : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_resumo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var palavrasViewModel: DadosViewModel? = null
        activity?.let {
            palavrasViewModel = ViewModelProviders.of(it).get(DadosViewModel::class.java)


            var dadosAdapter = DadosRecycleAdapter(palavrasViewModel!!.palavrasUtilizadas.value)
            rcyVwHome.adapter = dadosAdapter
            rcyVwHome.layoutManager = LinearLayoutManager(context)

          //  itemTouchHelper.attachToRecyclerView(rcyVwHome)

        }
    }
}
