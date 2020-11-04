package ru.haliksar.flowapp.features.news.shared.data.dto

import com.google.gson.annotations.SerializedName

data class UrlDto(
    @SerializedName("link") val link: String,
    @SerializedName("text") val text: String
)