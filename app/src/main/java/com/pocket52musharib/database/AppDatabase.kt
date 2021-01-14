package com.pocket52musharib.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pocket52musharib.BuildConfig
import com.pocket52musharib.api.model.UserInfo
import com.pocket52musharib.api.model.UserPost

/**
 * Created by Musharib Ali on 13/1/21.
 * I.S.T Pvt. Ltd
 * musharib.ali@innobles.com
 */
@Database(entities = [UserInfo::class,UserPost::class],version = 3,exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userList(): UserListDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, BuildConfig.BUILD_TYPE)
                .fallbackToDestructiveMigration()
                .build()
    }

}