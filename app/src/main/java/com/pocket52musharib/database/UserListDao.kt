package com.pocket52musharib.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.pocket52musharib.api.model.UserInfo
import com.pocket52musharib.api.model.UserPost

/**
 * Created by Musharib Ali on 13/1/21.
 * I.S.T Pvt. Ltd
 * musharib.ali@innobles.com
 */
@Dao
interface UserListDao {
    @Query("SELECT *,count(user.uId) as countPost FROM user left JOIN user_post ON user.uId = user_post.userId group by user.name order by uId")
    fun getAllUser(): LiveData<List<UserInfo>>



    @Query("SELECT *, user.name AS pName, user.email AS pEmail FROM user INNER JOIN user_post ON user.uId = user_post.userId where user_post.userId = :userId")
    fun getAllUserPost(userId: String):LiveData<List<UserPost>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<UserInfo>)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPost(post: List<UserPost>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users:UserInfo)

    @Query("SELECT * FROM user where uId =:userId")
    fun getUser(userId: String): LiveData<List<UserInfo>>

}