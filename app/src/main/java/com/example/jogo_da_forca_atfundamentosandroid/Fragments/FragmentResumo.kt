package com.example.jogo_da_forca_atfundamentosandroid.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import ViewModel.DadosViewModel
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jogo_da_forca_atfundamentosandroid.Adapter.DadosRecycleAdapter
import com.example.jogo_da_forca_atfundamentosandroid.MainActivity
import com.example.jogo_da_forca_atfundamentosandroid.Model.DadosModel

import com.example.jogo_da_forca_atfundamentosandroid.R
import kotlinx.android.synthetic.main.fragment_resumo.*
import kotlinx.android.synthetic.main.resumo_recycle.*

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

           // var dadosAdapter = DadosRecycleAdapter(palavrasViewModel.)
         //   rcyVwHome.adapter = dadosAdapter
          //  rcyVwHome.layoutManager = LinearLayoutManager(context)

          //  itemTouchHelper.attachToRecyclerView(rcyVwHome)

            buttonNovoJogo.setOnClickListener{
                val intent = Intent(activity?.baseContext, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
