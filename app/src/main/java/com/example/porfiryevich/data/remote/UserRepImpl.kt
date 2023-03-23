package com.example.porfiryevich.data.remote

import com.example.porfiryevich.domain.models.SResp
import com.example.porfiryevich.domain.repository.UserRep
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepImpl(private val porfiryevichApi: PorfiryevichApi): UserRep {
    override suspend fun sendAMessage(text: String, length: Int): SResp{
        return withContext(Dispatchers.Main){
            porfiryevichApi.sequel(RegistrationBody(text, length)).body()?.mapToSResp()
        }!!
    }
}