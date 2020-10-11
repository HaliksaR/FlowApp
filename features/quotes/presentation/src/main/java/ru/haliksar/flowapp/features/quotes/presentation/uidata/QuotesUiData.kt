package ru.haliksar.flowapp.features.quotes.presentation.uidata

import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.qualifier.named
import ru.haliksar.flowapp.features.quotes.domain.entity.QuotesEntity
import ru.haliksar.flowapp.libraries.core.data.mapper.MapperUiData
import ru.haliksar.flowapp.libraries.core.data.mapper.mapperUiData
import ru.haliksar.flowapp.libraries.core.presentation.di.AUTHOR_MAPPER_UIDATA
import ru.haliksar.flowapp.libraries.core.presentation.uidata.AuthorMapperUiDataT
import ru.haliksar.flowapp.libraries.core.presentation.uidata.AuthorUiData

data class QuotesUiData(
    val author: AuthorUiData,
    val quote: String
)

typealias QuotesMapperUiDataT = MapperUiData<QuotesEntity, QuotesUiData>

@KoinApiExtension
class QuotesMapperUiData : QuotesMapperUiDataT(), KoinComponent {

    private val authorMapper
            by mapperUiData<AuthorMapperUiDataT>(named(AUTHOR_MAPPER_UIDATA))

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