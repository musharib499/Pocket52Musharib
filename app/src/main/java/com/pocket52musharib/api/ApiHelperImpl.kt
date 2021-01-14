package com.pocket52musharib.api

import javax.inject.Inject

/**
 * Created by Musharib Ali on 12/1/21.
 * I.S.T Pvt. Ltd
 * musharib.ali@innobles.com
 */
class ApiHelperImpl @Inject constructor(private val apiService: ApiService): BaseDataSource() {
    suspend fun getUserList() = getResult { apiService.getUserList() }
    suspend fun getUserPostList() = getResult { apiService.getUserPostList() }
    suspend fun getUserListSearch(query:String) = getResult { apiService.getUserListSearch(query) }
}