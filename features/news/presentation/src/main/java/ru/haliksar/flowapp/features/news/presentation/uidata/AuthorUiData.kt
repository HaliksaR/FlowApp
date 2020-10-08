package ru.haliksar.flowapp.features.news.presentation.uidata

import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import ru.haliksar.flowapp.features.news.domain.entity.AuthorEntity
import ru.haliksar.flowapp.libraries.core.data.MapperUiData
import ru.haliksar.flowapp.libraries.core.data.mapperUiData

data class AuthorUiData(
    val name: String,
    val surname: String,
    val profileUrl: UrlUiData,
    val avatarUrl: String
)

typealias AuthorMapperUiDataT = MapperUiData<AuthorEntity, AuthorUiData>

@KoinApiExtension
class AuthorMapperUiData : AuthorMapperUiDataT(), KoinComponent {

    private val urlMapper by mapperUiData<UrlMapperUiDataT>()

    override fun toUiData(entity: AuthorEntity): AuthorUiData =
        AuthorUiData(
            entity.name,
            entity.surname,
            urlMapper.toUiData(entity.profileUrl),
            entity.avatarUrl,
        )

    override fun toEntity(uiData: AuthorUiData): AuthorEntity =
        AuthorEntity(
            uiData.name,
            uiData.surname,
            urlMapper.toEntity(uiData.profileUrl),
            uiData.avatarUrl,
        )
}