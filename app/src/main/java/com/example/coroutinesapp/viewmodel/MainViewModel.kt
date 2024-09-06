package com.example.coroutinesapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel:ViewModel() {

    var resultState by mutableStateOf("")
        private set

    var countTime by mutableStateOf(0)
        private set
    private var oneCount by mutableStateOf(false)

    fun fetchData(){
      val job =    viewModelScope.launch {
            for (i in 1..5){
                delay(1000)
                countTime = i
            }
          oneCount = true
        }

        viewModelScope.launch {
            delay(5000)
            resultState = "Respuesta desde el servidor Web"
        }

        if( oneCount){
            job.cancel()
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








}