package com.example.porfiryevich.data.models

import com.example.porfiryevich.domain.models.SResp

data class SequelResponse(val replies: List<String>){
    fun mapToSResp() = SResp(replies)
}