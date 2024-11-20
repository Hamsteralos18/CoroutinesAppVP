package com.example.coroutinesapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    var resultState by mutableStateOf("")
        private set

    var countTime1 by mutableStateOf(0)
        private set

    var countTime2 by mutableStateOf(0)
        private set

    private var job1: Job? = null
    private var job2: Job? = null

    fun startCounters() {
        // Inicia el primer contador y al terminar, el segundo contador
        job1 = viewModelScope.launch {
            for (i in 1..5) {
                delay(1000)
                countTime1 = i
            }
            startSecondCounter() // Inicia el segundo contador cuando el primero termine
        }
    }

    private suspend fun startSecondCounter() {
        job2 = viewModelScope.launch {
            for (i in 1..5) {
                delay(1000)
                countTime2 = i
            }
            resultState = "Tarea completada!"
        }
    }

    fun cancelCounters() {
        job1?.cancel()
        job2?.cancel()
        resultState = "Procesos cancelados"
    }
}




    /*
      Thread trabaja en el mismo contexto de la IU

    fun bloqueoApp(){
        Thread.sleep(5000)
        resultState = "Respuesta del Servidor Web"

    }
  */

    /*
    fun fetchData(){
        viewModelScope.launch {
            delay(5000)
            resultState = "Respuesta desde el Servidor Web"
        }
    }*/








