package com.pocket52musharib.di

import android.content.Context
import com.pocket52musharib.api.ApiHelperImpl
import com.pocket52musharib.api.ApiService
import com.pocket52musharib.database.AppDatabase
import com.pocket52musharib.database.UserListDao
import com.pocket52musharib.ui.Repository.UserListRep
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Musharib Ali on 12/1/21.
 * I.S.T Pvt. Ltd
 * musharib.ali@innobles.com
 */
@Module
@InstallIn(ApplicationComponent::class)
object ApplicationModule {
    var baseUrl ="https://jsonplaceholder.typicode.com/"
    @Provides
    @Singleton
    fun provideOkHttp() = run {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideApiHelper(helper: ApiService) = ApiHelperImpl(helper)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideUserListDao(db: AppDatabase) = db.userList()

    @Singleton
    @Provides
    fun provideClassConferenceListRepo(
        remoteDataSource: ApiHelperImpl,
        localDataSource: UserListDao
    ) = UserListRep(remoteDataSource, localDataSource)
}