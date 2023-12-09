package id.belitong.bigs.compose.core.domain.usecase

import id.belitong.bigs.compose.core.data.Resource
import id.belitong.bigs.compose.core.domain.model.Biodiversity
import id.belitong.bigs.compose.core.domain.model.Geosite
import id.belitong.bigs.compose.core.domain.model.Order
import id.belitong.bigs.compose.core.domain.model.Plant
import id.belitong.bigs.compose.core.domain.model.Report
import kotlinx.coroutines.flow.Flow

interface GeoparkUseCase {
    fun getGeosites(): Flow<Resource<List<Geosite>>>
    fun getBiodiversities(): Flow<Resource<List<Biodiversity>>>
    fun getPlants(): Flow<Resource<List<Plant>>>
    fun getOrders(): Flow<Resource<List<Order>>>
    fun getReports(): Flow<Resource<List<Report>>>
}