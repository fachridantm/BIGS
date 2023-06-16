package id.belitong.bigs.core.data.source.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import id.belitong.bigs.core.data.source.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferences @Inject constructor(private val dataStore: DataStore<Preferences>) {

    fun getAuthToken(): Flow<String> = dataStore.data.map { preferences ->
        preferences[AUTH_KEY] ?: ""
    }

    fun getUserId(): Flow<String> = dataStore.data.map { preferences ->
        preferences[ID_KEY] ?: ""
    }

    fun getName(): Flow<String> = dataStore.data.map { preferences ->
        preferences[NAME_KEY] ?: ""
    }

    suspend fun saveSession(token: String, user: UserEntity) = dataStore.edit { preferences ->
        preferences[AUTH_KEY] = token
        preferences[ID_KEY] = user.userId
        preferences[NAME_KEY] = user.name
    }

    suspend fun deleteSession() = dataStore.edit {
        it.clear()
    }

    companion object {
        private val AUTH_KEY = stringPreferencesKey("AUTH_KEY")
        private val ID_KEY = stringPreferencesKey("ID_KEY")
        private val NAME_KEY = stringPreferencesKey("NAME_KEY")
    }
}