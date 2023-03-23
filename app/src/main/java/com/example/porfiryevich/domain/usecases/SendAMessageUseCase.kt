package com.example.porfiryevich.domain.usecases

import com.example.porfiryevich.data.models.SequelResponse
import com.example.porfiryevich.domain.models.SResp
import com.example.porfiryevich.domain.repository.UserRep

class SendAMessageUseCase(private val repImpl: UserRep) {
    suspend fun execute(message: String, lenght: Int): SResp{
        return repImpl.sendAMessage(message, lenght)
    }
}