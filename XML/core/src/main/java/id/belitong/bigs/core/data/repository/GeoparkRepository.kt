package id.belitong.bigs.core.data.repository

import id.belitong.bigs.core.data.Resource.Companion.error
import id.belitong.bigs.core.data.Resource.Companion.loading
import id.belitong.bigs.core.data.Resource.Companion.success
import id.belitong.bigs.core.data.source.local.LocalDataSource
import id.belitong.bigs.core.data.source.remote.RemoteDataSource
import id.belitong.bigs.core.data.source.remote.network.ApiResponse
import id.belitong.bigs.core.domain.repository.IGeoparkRepository
import id.belitong.bigs.core.utils.DataMapper
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeoparkRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : IGeoparkRepository {
    override fun getGeosites() = flow {
        emit(loading())
        when (val apiResponse = remoteDataSource.getGeosites().first()) {
            is ApiResponse.Success -> {
                val data = DataMapper.geositeItemToGeosite(apiResponse.data)
                emit(success(data))
            }

            is ApiResponse.Error -> {
                emit(error(apiResponse.errorMessage))
            }

            is ApiResponse.Empty -> {}
        }
    }

    override fun getBiodiversities() = flow {
        emit(loading())
        when (val apiResponse = remoteDataSource.getBiodiversities().first()) {
            is ApiResponse.Success -> {
                val data = DataMapper.biodiversityItemToBiodiversity(apiResponse.data)
                emit(success(data))
            }

            is ApiResponse.Error -> {
                emit(error(apiResponse.errorMessage))
            }

            is ApiResponse.Empty -> {}
        }
    }

    override fun getPlant() = flow {
        emit(loading())
        when (val apiResponse = remoteDataSource.getPlant().first()) {
            is ApiResponse.Success -> {
                val data = DataMapper.plantResponseToPlant(apiResponse.data)
                emit(success(data))
            }

            is ApiResponse.Error -> {
                emit(error(apiResponse.errorMessage))
            }

            is ApiResponse.Empty -> {}
        }
    }

    override fun getOrders() = flow {
        emit(loading())
        when (val apiResponse = remoteDataSource.getOrders().first()) {
            is ApiResponse.Success -> {
                val data = DataMapper.orderItemToOrder(apiResponse.data)
                emit(success(data))
            }

            is ApiResponse.Error -> {
                emit(error(apiResponse.errorMessage))
            }

            is ApiResponse.Empty -> {}
        }
    }

    override fun getReports() = flow {
        emit(loading())
        when (val apiResponse = remoteDataSource.getReports().first()) {
            is ApiResponse.Success -> {
                val data = DataMapper.reportItemToReport(apiResponse.data)
                emit(success(data))
            }

            is ApiResponse.Error -> {
                emit(error(apiResponse.errorMessage))
            }

            is ApiResponse.Empty -> {}
        }
    }
}