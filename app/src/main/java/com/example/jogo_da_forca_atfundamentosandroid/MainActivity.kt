package com.example.jogo_da_forca_atfundamentosandroid

import ViewModel.DadosViewModel
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.ui.main.DeslizePagerAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        //viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        viewPager.adapter   = DeslizePagerAdapter(supportFragmentManager)
        val fab: FloatingActionButton = findViewById(R.id.fab)

        val model = ViewModelProviders.of(this)[DadosViewModel::class.java]

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Dicas", Snackbar.LENGTH_LONG)
                .setAction("Dicas", null).show()
           // searchWeb("imagem $texto")
            }


    }
    /*fun searchWeb(query: String) {
        val intent = Intent(Intent.ACTION_WEB_SEARCH).apply {
            putExtra(SearchManager.QUERY, query)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }*/
}

