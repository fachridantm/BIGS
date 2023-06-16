package id.belitong.bigs.core.domain.usecase

import id.belitong.bigs.core.data.Resource
import id.belitong.bigs.core.domain.model.Biodiversity
import id.belitong.bigs.core.domain.model.Geosite
import kotlinx.coroutines.flow.Flow

interface MainUseCase {
    fun getAllGeosites(): Flow<Resource<List<Geosite>>>

    fun getAllBiodiversity(): Flow<Resource<List<Biodiversity>>>
}