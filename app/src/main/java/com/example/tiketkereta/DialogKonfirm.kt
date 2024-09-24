package com.example.tiketkereta

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.example.tiketkereta.databinding.DialogKonfirmBinding

class DialogKonfirm : DialogFragment() {
    private lateinit var binding: DialogKonfirmBinding

    private var nama: String? = null
    private var jam: String? = null
    private var tanggal: String? = null
    private var tujuan: String? = null

    companion object {
        const val ARG_NAMA = "NAMA"
        const val ARG_JAM = "JAM"
        const val ARG_TANGGAL = "TANGGAL"
        const val ARG_TUJUAN = "TUJUAN"

        fun newInstance(nama: String, jam: String, tanggal: String, tujuan: String): DialogKonfirm {
            val dialog = DialogKonfirm()
            val args = Bundle()
            args.putString(ARG_NAMA, nama)
            args.putString(ARG_JAM, jam)
            args.putString(ARG_TANGGAL, tanggal)
            args.putString(ARG_TUJUAN, tujuan)
            dialog.arguments = args
            return dialog
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            nama = it.getString(ARG_NAMA)
            jam = it.getString(ARG_JAM)
            tanggal = it.getString(ARG_TANGGAL)
            tujuan = it.getString(ARG_TUJUAN)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.dialog_konfirm, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DialogKonfirmBinding.inflate(layoutInflater)

        val btnBatal = view.findViewById<Button>(R.id.btn_batal)
        val btnBeli = view.findViewById<Button>(R.id.btn_beli)

        btnBatal.setOnClickListener {
            dismiss()
        }

        btnBeli.setOnClickListener {
            navigateToConfirmation()
            dismiss()

        }
    }

    private fun navigateToConfirmation() {
        val intentToKonfirmActivity = Intent(requireActivity(), KonfirmActivity::class.java).apply {
            putExtra("username", nama)
            putExtra("jam", jam)
            putExtra("tanggal", tanggal)
            putExtra("tujuan", tujuan)
        }
        startActivity(intentToKonfirmActivity)
    }
}
