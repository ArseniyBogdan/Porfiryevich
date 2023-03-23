package com.example.porfiryevich.domain.repository

import com.example.porfiryevich.domain.models.SResp

interface UserRep {
    suspend fun sendAMessage(text: String, length: Int): SResp
}