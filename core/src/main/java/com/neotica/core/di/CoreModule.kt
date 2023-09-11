package com.neotica.core.di

import androidx.room.Room
import com.neotica.core.data.CharacterRepository
import com.neotica.core.data.source.local.LocalDataSource
import com.neotica.core.data.source.local.room.CharacterDatabase
import com.neotica.core.data.source.remote.RemoteDataSource
import com.neotica.core.data.source.remote.network.ApiService
import com.neotica.core.domain.repository.ICharacterRepository
import com.neotica.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<CharacterDatabase>().characterDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("neotica".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(), CharacterDatabase::class.java, "Rick.db"
        )
            .fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val logBody = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val loggingInterceptor = if (BuildConfig.DEBUG) {
            logBody
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<ICharacterRepository> { CharacterRepository(get(), get(), get()) }
}