package com.pocket52musharib.ui.viewModule

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.pocket52musharib.api.LoadingState
import com.pocket52musharib.api.Resource
import com.pocket52musharib.database.UserListDao
import com.pocket52musharib.ui.Repository.UserListRep
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by Musharib Ali on 14/1/21.
 * I.S.T Pvt. Ltd
 * musharib.ali@innobles.com
 */
class SplashViewModule @ViewModelInject constructor(private val repo: UserListRep, private val localDataSource: UserListDao) : ViewModel() {
   val _update = MutableLiveData<Boolean>()
    fun loadData(lifecycleOwner: LifecycleOwner){
        repo.getUserPostList().observe(lifecycleOwner,{
            when(it.status) {
                Resource.Status.LOADING ->{}
                Resource.Status.SUCCESS -> { }
                Resource.Status.ERROR ->{}
            }
        })
        repo.getUserList().observe(lifecycleOwner,{
            when(it.status) {
                Resource.Status.LOADING ->{}
                Resource.Status.SUCCESS -> { _update.postValue(true)}
                Resource.Status.ERROR ->{ _update.postValue(true)}
            }

        })

}




}