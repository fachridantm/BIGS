package id.belitong.bigs.core.data.repository

import id.belitong.bigs.core.data.Resource
import id.belitong.bigs.core.data.source.local.LocalDataSource
import id.belitong.bigs.core.data.source.remote.RemoteDataSource
import id.belitong.bigs.core.data.source.remote.network.ApiResponse
import id.belitong.bigs.core.domain.model.Biodiversity
import id.belitong.bigs.core.domain.model.Geosite
import id.belitong.bigs.core.domain.model.Order
import id.belitong.bigs.core.domain.repository.IMainRepository
import id.belitong.bigs.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : IMainRepository {

    override fun getAllGeosites(): Flow<Resource<List<Geosite>>> = flow {
        emit(Resource.Loading())
        when (val response = remoteDataSource.getAllGeosites().first()) {
            is ApiResponse.Success -> {
                val geosites = response.data.map {
                    DataMapper.geositeResponseToGeosite(it)
                }
                emit(Resource.Success(geosites))
            }

            is ApiResponse.Error -> {
                emit(Resource.Error(response.errorMessage))
            }

            is ApiResponse.Empty -> {
                emit(Resource.Success(emptyList()))
                emit(Resource.Error("Data is empty"))
            }
        }
    }

    override fun getAllBiodiversity(): Flow<Resource<List<Biodiversity>>> = flow {
        emit(Resource.Loading())
        when (val response = remoteDataSource.getAllBiodiversity().first()) {
            is ApiResponse.Success -> {
                val biodiversity = response.data.map {
                    DataMapper.biodiversityResponseToBiodiversity(it)
                }
                emit(Resource.Success(biodiversity))
            }

            is ApiResponse.Error -> {
                emit(Resource.Error(response.errorMessage))
            }

            is ApiResponse.Empty -> {
                emit(Resource.Success(emptyList()))
                emit(Resource.Error("Data is empty"))
            }
        }
    }

    override fun getAllOrder(): Flow<Resource<List<Order>>> = flow {
        emit(Resource.Loading())
        when (val response = remoteDataSource.getAllOrder().first()) {
            is ApiResponse.Success -> {
                val order = response.data.map {
                    DataMapper.orderResponseToOrder(it)
                }
                emit(Resource.Success(order))
            }

            is ApiResponse.Error -> {
                emit(Resource.Error(response.errorMessage))
            }

            is ApiResponse.Empty -> {
                emit(Resource.Success(emptyList()))
                emit(Resource.Error("Data is empty"))
            }
        }
    }
}