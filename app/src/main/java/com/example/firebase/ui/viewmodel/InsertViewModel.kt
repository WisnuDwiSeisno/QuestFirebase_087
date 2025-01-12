package com.example.firebase.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.firebase.model.Mahasiswa
import com.example.firebase.repository.MahasiswaRepository

class InsertViewModel(private val mhs: MahasiswaRepository) : ViewModel() {

}


// data class variabel yang menyimpan data input form
data class MahasiswaEvent(

    val nim: String = "",
    val nama: String = "",
    val jenis_kelamin: String = "",
    val alamat: String = "",
    val kelas: String = "",
    val angkatan: String = "",
)