package id.belitong.bigs.compose.core.data

import id.belitong.bigs.compose.core.data.Resource.Companion.loading
import id.belitong.bigs.compose.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

abstract class NetworkBoundResource<ResultType : Any, RequestType> {
    private var result: Flow<Resource<ResultType>> = flow {
        emit(loading())
        val dbSource = loadFromDB().first()
        if (shouldFetch(dbSource)) {
            emit(loading())
            when (val apiResponse = createCall().first()) {
                is ApiResponse.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(
                        loadFromDB().map {
                            Resource.Success(it)
                        }
                    )
                }

                is ApiResponse.Empty -> {
                    emitAll(
                        loadFromDB().map {
                            Resource.Success(it)
                        }
                    )
                }

                is ApiResponse.Error -> {
                    onFetchFailed()
                    emit(Resource.Error(apiResponse.errorMessage))
                }
            }
        } else {
            emitAll(
                loadFromDB().map {
                    Resource.Success(it)
                }
            )
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<Resource<ResultType>> = result
}