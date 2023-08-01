package id.belitong.bigs.core.data.source.local

import id.belitong.bigs.core.data.source.local.datastore.UserPreferences
import id.belitong.bigs.core.data.source.local.entity.UserEntity
import id.belitong.bigs.core.data.source.local.room.PlantDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val userPreferences: UserPreferences,
    private val plantDao: PlantDao,
) {
    fun getAuthToken(): Flow<String> = userPreferences.getAuthToken()

    fun getUserId(): Flow<String> = userPreferences.getUserId()

    fun getName(): Flow<String> = userPreferences.getName()

    suspend fun saveSession(token: String, user: UserEntity) =
        userPreferences.saveSession(token, user)

    suspend fun deleteSession() = userPreferences.deleteSession()
}