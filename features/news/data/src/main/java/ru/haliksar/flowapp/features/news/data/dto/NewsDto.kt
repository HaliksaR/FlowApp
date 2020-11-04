package ru.haliksar.flowapp.features.news.data.dto

import com.google.gson.annotations.SerializedName
import ru.haliksar.flowapp.features.news.shared.data.dto.AuthorDto
import ru.haliksar.flowapp.features.news.shared.data.dto.UrlDto
import java.util.*

data class NewsDto(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("author") val author: AuthorDto,
    @SerializedName("pictures") val pictures: List<UrlDto>?,
    @SerializedName("postDate") val postDate: Date
)