package com.pocket52musharib.api.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "user_post")
data class UserPost(
        @SerializedName("name")
        var pName: String?,
        @SerializedName("email")
        var pEmail: String?,
        @SerializedName("body")
        var body: String?, // cupiditate quo est a modi nesciunt solutaipsa voluptas error itaque dicta inautem qui minus magnam et distinctio eumaccusamus ratione error aut
        @SerializedName("id")
        @PrimaryKey
        var pId: Int?, // 100
        @SerializedName("title")
        var title: String?, // at nam consequatur ea labore ea harum
        @SerializedName("userId")
        var userId: Int? // 10
    )