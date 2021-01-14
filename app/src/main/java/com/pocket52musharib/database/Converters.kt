package com.pocket52musharib.database

import androidx.room.TypeConverter
import java.util.*

/**
 * Created by Musharib Ali on 13/1/21.
 * I.S.T Pvt. Ltd
 * musharib.ali@innobles.com
 */
class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

//    @TypeConverter
//    fun getUserInfoToString(someObjects: List<UserInfoResponse.UserInfo>?): String? {
//        return Gson().toJson(someObjects)
//    }
//
//    @TypeConverter
//    fun fromUserInfoTimestamp(data: String?): List<UserInfoResponse.UserInfo>? {
//
//        if (data == null) {
//            return Collections.emptyList()
//        }
//        val listType =
//            object : TypeToken<ArrayList<UserInfoResponse.UserInfo>>() {}.type
//        return Gson().fromJson(data, listType)
//
//
//    }
}