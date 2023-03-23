package com.example.porfiryevich.ui.main

import android.app.Application
import android.text.TextUtils
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.porfiryevich.RetrofitSingleton
import com.example.porfiryevich.data.remote.UserRepImpl
import com.example.porfiryevich.domain.usecases.SendAMessageUseCase
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.SocketTimeoutException
import java.util.*

class MainViewModel() : ViewModel() {
    private var countOfWords: Int = 30
    private val userRepImpl = UserRepImpl(RetrofitSingleton.getRetrofitApi())
    private val sendAMessageUseCase = SendAMessageUseCase(userRepImpl)
    val message = MutableStateFlow("Привет")
    val failedSend = MutableStateFlow(false)
    val validateFailure = MutableStateFlow(false)

    fun sendAMessage(text: String){
        if(text.isEmpty()){
            return
        }
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                try {
                    message.value = sendAMessageUseCase.execute(text, countOfWords).replies[0]
                }
                catch (e: SocketTimeoutException){
                    failedSend.value = true
                }
            }
        }
    }

    private fun validateInput(value: String): Boolean{
        if (TextUtils.isEmpty(value)) {
            validateFailure.value = true
            return false
        }
        else if(value.isDigitsOnly() && value.toInt() > 0 && value.toInt() <= 50){
            return true
        }
        validateFailure.value = true
        return false
    }

    fun resetCountOfWords(value: String){
        if(validateInput(value)){
            countOfWords = value.toInt()
        }
    }

}