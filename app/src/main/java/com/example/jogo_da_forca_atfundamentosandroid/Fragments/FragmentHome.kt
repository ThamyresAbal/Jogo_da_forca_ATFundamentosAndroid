package com.example.jogo_da_forca_atfundamentosandroid.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.example.jogo_da_forca_atfundamentosandroid.Model.DadosModel
import ViewModel.DadosViewModel
import android.widget.Toast

import com.example.jogo_da_forca_atfundamentosandroid.R
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class FragmentHome : Fragment() {
    private lateinit var mPager: ViewPager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var dadosViewModel : DadosViewModel? = null
        activity?.let {
            dadosViewModel = ViewModelProviders.of(it).get(DadosViewModel::class.java)
        }

        buttonIniciarJogo.setOnClickListener {
            if(editTextNomeJogador.text.isNullOrBlank())
                Toast.makeText(activity?.baseContext,"Digite seu nome",Toast.LENGTH_SHORT).show()
            dadosViewModel?.dadoUsuario= DadosModel(
                editTextNomeJogador.text.toString()
            )
            findNavController().navigate(R.id.fragmentJogo)

        }

    }
}
