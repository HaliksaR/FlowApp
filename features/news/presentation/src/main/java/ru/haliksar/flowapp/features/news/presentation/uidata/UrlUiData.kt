package ru.haliksar.flowapp.features.news.presentation.uidata

import ru.haliksar.flowapp.features.news.domain.entity.UrlEntity
import ru.haliksar.flowapp.libraries.core.data.MapperUiData

data class UrlUiData(
    val link: String,
    val text: String
)

typealias UrlMapperUiDataT = MapperUiData<UrlEntity, UrlUiData>

class UrlMapperUiData : UrlMapperUiDataT() {

    override fun toUiData(entity: UrlEntity): UrlUiData =
        UrlUiData(entity.link, entity.text)

    override fun toEntity(uiData: UrlUiData): UrlEntity =
        UrlEntity(
            uiData.link,
            uiData.text
        )
}
