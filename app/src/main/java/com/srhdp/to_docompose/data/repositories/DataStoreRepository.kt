package com.srhdp.to_docompose.data.repositories

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.*
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.srhdp.to_docompose.data.models.Priority
import com.srhdp.to_docompose.util.Constants.PREFERENCE_KEY
import com.srhdp.to_docompose.util.Constants.PREFERENCE_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)

@ViewModelScoped
class DataStoreRepository @Inject constructor(
    @ApplicationContext private val context:Context
) {
    private object PreferenceKey{
        val sortKey = stringPreferencesKey(name = PREFERENCE_KEY)
    }
    private val dataStore = context.dataStore

    suspend fun persistSortState(priority: Priority){
        dataStore.edit { preference ->
            preference[PreferenceKey.sortKey] = priority.name
        }
    }

    val readSortState: Flow<String> = dataStore.data
        .catch { exception ->
            if(exception is IOException){
                emit(emptyPreferences())
            }else{
                throw exception
            }
        }
        .map { preference ->
            val sortState = preference[PreferenceKey.sortKey]?:Priority.NONE.name
            sortState
        }
}