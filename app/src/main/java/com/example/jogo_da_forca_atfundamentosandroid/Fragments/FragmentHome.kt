package com.example.jogo_da_forca_atfundamentosandroid.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.example.jogo_da_forca_atfundamentosandroid.Model.DadosModel
import ViewModel.DadosViewModel
import android.content.Intent
import android.widget.Toast
import com.example.jogo_da_forca_atfundamentosandroid.InicioActivity

import com.example.jogo_da_forca_atfundamentosandroid.R
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class FragmentHome : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var palavrasViewModel : DadosViewModel? = null
        activity?.let {
            palavrasViewModel = ViewModelProviders.of(it).get(DadosViewModel::class.java)
        }


        buttonIniciar.setOnClickListener {
            if (editTextNomeJogador.text.isNullOrBlank())
                Toast.makeText(activity?.baseContext, "Digite seu nome", Toast.LENGTH_SHORT).show()

            palavrasViewModel?.dadoUsuario = DadosModel(editTextNomeJogador.text.toString(),"teste"
            )
           val intent = Intent(activity?.baseContext, InicioActivity::class.java)
            startActivity(intent)
        }
    }
}
