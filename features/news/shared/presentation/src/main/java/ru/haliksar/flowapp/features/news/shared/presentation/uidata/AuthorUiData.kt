package ru.haliksar.flowapp.features.news.shared.presentation.uidata

import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.qualifier.named
import ru.haliksar.flowapp.features.news.shared.domain.entity.AuthorEntity
import ru.haliksar.flowapp.features.news.shared.presentation.di.URL_MAPPER_UIDATA
import ru.haliksar.flowapp.libraries.core.data.mapper.MapperUiData
import ru.haliksar.flowapp.libraries.core.data.mapper.mapperUiData

data class AuthorUiData(
    val name: String,
    val surname: String,
    val profileUrl: UrlUiData,
    val avatarUrl: String
)

typealias AuthorMapperUiDataT = MapperUiData<AuthorEntity, AuthorUiData>

@KoinApiExtension
class AuthorMapperUiData : AuthorMapperUiDataT(), KoinComponent {

    private val urlMapper by mapperUiData<UrlMapperUiDataT>(named(URL_MAPPER_UIDATA))

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