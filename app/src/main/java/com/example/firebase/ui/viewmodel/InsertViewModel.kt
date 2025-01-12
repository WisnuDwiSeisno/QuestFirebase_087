package com.example.firebase.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.firebase.model.Mahasiswa
import com.example.firebase.repository.MahasiswaRepository

class InsertViewModel(private val mhs: MahasiswaRepository) : ViewModel() {

}

// Menyimpan input form kedalam entity
fun MahasiswaEvent.toMhsModel(): Mahasiswa = Mahasiswa(
    nim = nim,
    nama = nama,
    jenis_kelamin = jenis_kelamin,
    alamat = alamat,
    kelas = kelas,
    angkatan = angkatan
)

// data class variabel yang menyimpan data input form
data class MahasiswaEvent(
    val nim: String = "",
    val nama: String = "",
    val jenis_kelamin: String = "",
    val alamat: String = "",
    val kelas: String = "",
    val angkatan: String = "",
)