package ru.haliksar.flowapp.features.quotes.data.dto

import com.google.gson.annotations.SerializedName
import ru.haliksar.flowapp.features.news.shared.data.dto.AuthorDto


data class QuotesDto(
    @SerializedName("author") val author: AuthorDto,
    @SerializedName("quote") val quote: String
)