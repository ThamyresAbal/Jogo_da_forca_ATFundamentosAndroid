package com.example.myapplication.ui.main

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.jogo_da_forca_atfundamentosandroid.Fragments.FragmentHome
import com.example.jogo_da_forca_atfundamentosandroid.Fragments.FragmentSobreJogo
import com.example.jogo_da_forca_atfundamentosandroid.R


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class DeslizePagerAdapter (fm:   FragmentManager):
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    val numeroDeFragmentos = 2
    val titulos = listOf("Home","Regras")

    override fun  getItem (position: Int) = when (position){
        0 -> FragmentHome()
        else -> FragmentSobreJogo()
    }
    //   retorna   o   título   adequado   à   aba   do   índice
    override fun  getPageTitle (position: Int ) =   titulos[position]
    override fun  getCount ()   =   numeroDeFragmentos
}