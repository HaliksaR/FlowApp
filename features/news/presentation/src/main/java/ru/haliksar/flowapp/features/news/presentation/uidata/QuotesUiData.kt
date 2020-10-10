package ru.haliksar.flowapp.features.news.presentation.uidata

import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.qualifier.named
import ru.haliksar.flowapp.features.news.domain.entity.QuotesEntity
import ru.haliksar.flowapp.features.news.presentation.di.AUTHOR_MAPPER_UIDATA
import ru.haliksar.flowapp.libraries.core.data.MapperUiData
import ru.haliksar.flowapp.libraries.core.data.mapperUiData

data class QuotesUiData(
    val author: AuthorUiData,
    val quote: String
)

typealias QuotesMapperUiDataT = MapperUiData<QuotesEntity, QuotesUiData>

@KoinApiExtension
class QuotesMapperUiData : QuotesMapperUiDataT(), KoinComponent {

    private val authorMapper by mapperUiData<AuthorMapperUiDataT>(named(AUTHOR_MAPPER_UIDATA))

    override fun toUiData(entity: QuotesEntity): QuotesUiData =
        QuotesUiData(
            authorMapper.toUiData(entity.author),
            entity.quote,
        )

    override fun toEntity(uiData: QuotesUiData): QuotesEntity =
        QuotesEntity(
            authorMapper.toEntity(uiData.author),
            uiData.quote
        )
}