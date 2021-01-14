package com.pocket52musharib.ui.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.pocket52musharib.api.ApiHelperImpl
import com.pocket52musharib.api.loadData
import com.pocket52musharib.database.UserListDao
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * Created by Musharib Ali on 13/1/21.
 * I.S.T Pvt. Ltd
 * musharib.ali@innobles.com
 */
class UserListRep @Inject constructor(private val remoteDataSource: ApiHelperImpl,private val localDataSource: UserListDao){
    fun getUserList() = loadData(databaseQuery = {localDataSource.getAllUser()},networkCall = {remoteDataSource.getUserList()},saveCallResult = {localDataSource.insertAll(it)})
    fun getUserPostList() = loadData(databaseQuery = {localDataSource.getAllUserPost("1")},networkCall = {remoteDataSource.getUserPostList()},saveCallResult = {localDataSource.insertAllPost(it)})
    fun getUserListSearch(query:String) = loadData(networkCall = {remoteDataSource.getUserListSearch("user/$query")})
    fun getUserListLocalSearch(query:String) = loadData(databaseQuery = {localDataSource.getUser(query)})
    fun getUserListLocal() = loadData(databaseQuery = {localDataSource.getAllUser()})
    fun getUserListLocalPost(userId:String) = loadData(databaseQuery = {localDataSource.getAllUserPost(userId)})
}