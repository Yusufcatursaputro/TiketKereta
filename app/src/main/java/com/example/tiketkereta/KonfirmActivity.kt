package com.example.tiketkereta

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class KonfirmActivity : AppCompatActivity() {

    private lateinit var ticketTitle: TextView
    private lateinit var ticketIcon: ImageView
    private lateinit var namaText: TextView
    private lateinit var jamText: TextView
    private lateinit var tanggalText: TextView
    private lateinit var tujuanText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_konfirm)

        ticketTitle = findViewById(R.id.ticketTitle)
        ticketIcon = findViewById(R.id.ticketIcon)
        namaText = findViewById(R.id.namaText)
        jamText = findViewById(R.id.jamText)
        tanggalText = findViewById(R.id.tanggalText)
        tujuanText = findViewById(R.id.tujuanText)

        val username = intent.getStringExtra("username")
        val jam = intent.getStringExtra("jam")
        val tanggal = intent.getStringExtra("tanggal")
        val tujuan = intent.getStringExtra("tujuan")

        namaText.text = "Nama: $username"
        jamText.text = "Jam: $jam"
        tanggalText.text = "Tanggal: $tanggal"
        tujuanText.text = "Tujuan: $tujuan"
    }
}
