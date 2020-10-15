package co.nimblehq.di.modules.storage

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.room.Room
import co.nimblehq.data.crypto.AESCrypto
import co.nimblehq.data.storage.DATABASE_NAME
import co.nimblehq.data.storage.SecureStorage
import co.nimblehq.data.storage.SecureStorageImpl
import co.nimblehq.data.storage.SurveyDatabase
import dagger.*
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class StorageModule {

    @Provides
    @Singleton
    fun provideSecureStorage(preferences: SharedPreferences, crypto: AESCrypto): SecureStorage = SecureStorageImpl(preferences, crypto)

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Provides
    @Singleton
    fun provideSurveyDatabase(@ApplicationContext context: Context): SurveyDatabase {
        return Room.databaseBuilder(context, SurveyDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
}
