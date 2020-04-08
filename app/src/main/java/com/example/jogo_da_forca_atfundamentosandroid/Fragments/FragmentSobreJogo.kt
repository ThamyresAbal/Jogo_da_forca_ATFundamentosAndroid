package com.example.jogo_da_forca_atfundamentosandroid.Fragments

import android.app.SearchManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.jogo_da_forca_atfundamentosandroid.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_sobre_jogo.*

/**
 * A simple [Fragment] subclass.
 */
class FragmentSobreJogo : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sobre_jogo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonSaibaMais.setOnClickListener {
            val url: String = "https://pt.wikipedia.org/wiki/Jogo_da_forca"
            val webPage: Uri = Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW,webPage)
            if(intent.resolveActivity(activity!!.packageManager) != null){
                startActivity(intent)
            }

        }


    }

}