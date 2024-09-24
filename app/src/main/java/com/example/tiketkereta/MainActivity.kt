package com.example.tiketkereta

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var usernameEditText: TextInputEditText
    private lateinit var jamKeberangkatanEditText: TextInputEditText
    private lateinit var tanggalKeberangkatanEditText: TextInputEditText
    private lateinit var tujuanAutoCompleteTextView: AutoCompleteTextView
    private lateinit var btnKonfirm: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usernameEditText = findViewById(R.id.Username)
        jamKeberangkatanEditText = findViewById(R.id.jam_keberangkatan)
        tanggalKeberangkatanEditText = findViewById(R.id.tanggal_keberangkatan)
        tujuanAutoCompleteTextView = findViewById(R.id.autoCompleteTextView)
        btnKonfirm = findViewById(R.id.btn_konfirm)

        val provinces = resources.getStringArray(R.array.provinces)
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, provinces)
        tujuanAutoCompleteTextView.setAdapter(adapter)
        tujuanAutoCompleteTextView.threshold = 1

        tanggalKeberangkatanEditText.setOnClickListener {
            showDatePicker()
        }

        jamKeberangkatanEditText.setOnClickListener {
            showTimePicker()
        }

        btnKonfirm.setOnClickListener {
            handleBooking()
        }

        jamKeberangkatanEditText.keyListener = null
        tanggalKeberangkatanEditText.keyListener = null
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val datePicker = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, month, dayOfMonth)
                val sdf = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
                tanggalKeberangkatanEditText.setText(sdf.format(selectedDate.time))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()
    }

    private fun showTimePicker() {
        val calendar = Calendar.getInstance()
        val timePicker = TimePickerDialog(
            this,
            { _, hourOfDay, minute ->
                val selectedTime = Calendar.getInstance()
                selectedTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
                selectedTime.set(Calendar.MINUTE, minute)
                val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
                jamKeberangkatanEditText.setText(sdf.format(selectedTime.time))
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        )
        timePicker.show()
    }

    private fun handleBooking() {
        val username = usernameEditText.text.toString().trim()
        val jam = jamKeberangkatanEditText.text.toString().trim()
        val tanggal = tanggalKeberangkatanEditText.text.toString().trim()
        val tujuan = tujuanAutoCompleteTextView.text.toString().trim()

        if (username.isEmpty()) {
            usernameEditText.error = "Nama Pemesan tidak boleh kosong"
            usernameEditText.requestFocus()
            return
        }

        if (jam.isEmpty()) {
            jamKeberangkatanEditText.error = "Jam Keberangkatan tidak boleh kosong"
            jamKeberangkatanEditText.requestFocus()
            return
        }

        if (tanggal.isEmpty()) {
            tanggalKeberangkatanEditText.error = "Tanggal Keberangkatan tidak boleh kosong"
            tanggalKeberangkatanEditText.requestFocus()
            return
        }

        if (tujuan.isEmpty()) {
            tujuanAutoCompleteTextView.error = "Tujuan tidak boleh kosong"
            tujuanAutoCompleteTextView.requestFocus()
            return
        }

        val dialog = DialogKonfirm.newInstance(nama = username, jam = jam, tanggal = tanggal, tujuan = tujuan)
        dialog.show(supportFragmentManager, "DialogKonfirm")
    }
}
