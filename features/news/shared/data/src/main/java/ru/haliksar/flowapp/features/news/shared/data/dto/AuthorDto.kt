package ru.haliksar.flowapp.features.news.shared.data.dto

import com.google.gson.annotations.SerializedName

data class AuthorDto(
    @SerializedName("name") val name: String,
    @SerializedName("surname") val surname: String,
    @SerializedName("profileUrl") val profileUrl: UrlDto,
    @SerializedName("avatarUrl") val avatarUrl: String
)