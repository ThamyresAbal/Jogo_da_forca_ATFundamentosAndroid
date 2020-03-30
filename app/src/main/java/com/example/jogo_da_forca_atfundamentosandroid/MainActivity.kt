package com.example.jogo_da_forca_atfundamentosandroid

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Telephony
import android.service.carrier.CarrierMessagingService
import android.telephony.SmsManager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.ui.main.SectionsPagerAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import java.net.URI


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        val fab: FloatingActionButton = findViewById(R.id.fab)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Convide seus amigos.", Snackbar.LENGTH_LONG)
                .setAction("SMS", null).show()
            composeMmsMessage("teste123", Uri.EMPTY)
        }

    }fun composeMmsMessage(message: String, attachment: Uri) {
        val smsManager: SmsManager = SmsManager.getDefault()
        smsManager.sendTextMessage("987654321", null, "Texto SMS",
            null, null)
        val intent = Intent(Intent.ACTION_SEND).apply {
            data = Uri.parse("teste:")  // This ensures only SMS apps respond
            putExtra("oioi", message)
            putExtra(Intent.EXTRA_STREAM, attachment)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }

    }
}
