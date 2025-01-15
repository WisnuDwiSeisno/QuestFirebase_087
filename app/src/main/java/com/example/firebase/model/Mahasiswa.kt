package com.example.firebase.model

data class Mahasiswa(
    val nim: String,
    val nama: String,
    val alamat: String,
    val jenis_kelamin: String,
    val kelas: String,
    val dospem1: String,
    val dospem2: String,
    val judul: String,
    val angkatan: String
) {
    constructor() : this("", "", "", "","","","", "", "")
}

