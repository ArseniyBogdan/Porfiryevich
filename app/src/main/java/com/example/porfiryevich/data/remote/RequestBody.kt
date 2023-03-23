package com.example.porfiryevich.data.remote

import com.google.gson.annotations.SerializedName

data class RegistrationBody(@SerializedName("prompt") val prompt: String,
                            @SerializedName("length") val length: Int)