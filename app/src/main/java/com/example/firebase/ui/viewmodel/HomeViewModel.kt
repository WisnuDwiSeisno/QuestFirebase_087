package com.example.firebase.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebase.model.Mahasiswa
import com.example.firebase.repository.MahasiswaRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

sealed class HomeUiState {
    data class Success(val mahasiswa: List<Mahasiswa>) : HomeUiState()
    data class Error(val message: Throwable) : HomeUiState()
    object Loading : HomeUiState()
}

class HomeViewModel(private val mhs: MahasiswaRepository) : ViewModel(){
    var mhsUIState: HomeUiState by mutableStateOf(HomeUiState.Loading)
    private set

    init {
        getMhs()
    }

    fun getMhs() {
        viewModelScope.launch {
            mhs.getMahasiswa()
                .onStart {
                    mhsUIState = HomeUiState.Loading
                }
                .catch {
                    mhsUIState = HomeUiState.Error(it)
                }
                .collect{
                    mhsUIState = if (it.isEmpty()){
                        HomeUiState.Error(Exception("Belum ada data"))
                    }
                    else{
                        HomeUiState.Success(it)
                    }
                }
        }
    }
    fun deleteMhs(mahasiswa: Mahasiswa) {
        viewModelScope.launch {
            try {
                // Hapus mahasiswa
                mhs.deleteMahasiswa(mahasiswa)

                // Dapatkan daftar mahasiswa terbaru setelah penghapusan
                val updatedList = mhs.getMahasiswa().first()

                // Perbarui state
                mhsUIState = if (updatedList.isEmpty()) {
                    HomeUiState.Error(Exception("Belum ada data"))
                } else {
                    HomeUiState.Success(updatedList)
                }
            } catch (e: Exception) {
                // Tangani error saat penghapusan
                mhsUIState = HomeUiState.Error(e)
            }
        }
    }
}