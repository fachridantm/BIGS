package id.belitong.bigs.core.data.source.local

import id.belitong.bigs.core.data.source.local.datastore.UserPreferences
import id.belitong.bigs.core.data.source.local.entity.UserEntity
import id.belitong.bigs.core.data.source.local.room.PlantDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
class LocalDataSource(
    private val userPreferences: UserPreferences,
    private val plantDao: PlantDao,
) {
    fun getToken(): Flow<String> = userPreferences.getAuthToken()

    fun getId(): Flow<Int> = userPreferences.getId()

    fun getName(): Flow<String> = userPreferences.getName()

    fun getEmail(): Flow<String> = userPreferences.getEmail()

    fun getPhoneNumber(): Flow<String> = userPreferences.getPhoneNumber()

    suspend fun saveSession(token: String, user: UserEntity) = userPreferences.saveSession(token, user)

    suspend fun deleteSession() = userPreferences.deleteSession()
}