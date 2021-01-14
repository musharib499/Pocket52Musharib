package com.pocket52musharib.api

import com.pocket52musharib.api.model.UserInfo
import com.pocket52musharib.api.model.UserPost
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by Musharib Ali on 12/1/21.
 * I.S.T Pvt. Ltd
 * musharib.ali@innobles.com
 */
interface ApiService {
   @GET("users")
   suspend fun getUserList(): Response<List<UserInfo>>

    @GET("posts")
    suspend fun getUserPostList(): Response<List<UserPost>>

   @GET
   suspend fun getUserListSearch(@Url query:String): Response<UserInfo>

}