package com.pocket52musharib.api.model


import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "user")
data class UserInfo(
        @Embedded
        @SerializedName("address")
        var address: Address?,
        @Embedded
        @SerializedName("company")
        var company: Company?,
        @SerializedName("email")
        var email: String?, // Sincere@april.biz
        @PrimaryKey
        @SerializedName("id")
        var uId: Int = 0, // 1
        @SerializedName("name")
        var name: String?, // Leanne Graham
        @SerializedName("phone")
        var phone: String?, // 1-770-736-8031 x56442
        @SerializedName("username")
        var username: String?, // Bret
        @SerializedName("website")
        var website: String?, // hildegard.org
        @SerializedName("countPost")
        var countPost: Int? = 0
    ) {
        data class Address(
            @SerializedName("city")
            var city: String?, // Gwenborough
            @Embedded
            @SerializedName("geo")
            var geo: Geo?,
            @SerializedName("street")
            var street: String?, // Kulas Light
            @SerializedName("suite")
            var suite: String?, // Apt. 556
            @SerializedName("zipcode")
            var zipcode: String? // 92998-3874
        ) {
            data class Geo(

                @SerializedName("lat")
                var lat: String?, // -37.3159
                @SerializedName("lng")
                var lng: String? // 81.1496
            )
        }

        data class Company(
            @SerializedName("bs")
            var bs: String?, // harness real-time e-markets
            @SerializedName("catchPhrase")
            var catchPhrase: String?, // Multi-layered client-server neural-net
            @ColumnInfo(name = "company_")
            @SerializedName("name")
            var name: String? // Romaguera-Crona
        )
    }