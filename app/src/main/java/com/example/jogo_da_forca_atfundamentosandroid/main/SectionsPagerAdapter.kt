package com.example.myapplication.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.jogo_da_forca_atfundamentosandroid.Fragments.FragmentHome
import com.example.jogo_da_forca_atfundamentosandroid.Fragments.FragmentSobreJogo
import com.example.jogo_da_forca_atfundamentosandroid.R

private val TAB_TITLES = listOf(
        R.string.tab_text_1,
        R.string.tab_text_2
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager)
    : FragmentPagerAdapter(fm) {

    val numeroDeFragmentos = 2

    override fun getItem(position: Int) = when (position) {
            1 -> FragmentHome()

            else -> FragmentSobreJogo()
    }

    override fun getPageTitle(position: Int)= TAB_TITLES[position]

    override fun getCount() = numeroDeFragmentos
}