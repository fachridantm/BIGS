package id.belitong.bigs.core.domain.repository

import id.belitong.bigs.core.data.Resource
import id.belitong.bigs.core.domain.model.Biodiversity
import id.belitong.bigs.core.domain.model.Geosite
import id.belitong.bigs.core.domain.model.Order
import id.belitong.bigs.core.domain.model.Plant
import id.belitong.bigs.core.domain.model.Report
import kotlinx.coroutines.flow.Flow

interface IGeoparkRepository {
    fun getGeosites(): Flow<Resource<List<Geosite>>>
    fun getBiodiversities(): Flow<Resource<List<Biodiversity>>>
    fun getPlant(): Flow<Resource<Plant>>
    fun getOrders(): Flow<Resource<List<Order>>>
    fun getReports(): Flow<Resource<List<Report>>>
}