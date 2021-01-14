package com.pocket52musharib.ui.viewModule

import android.util.Log
import android.widget.Toast
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.pocket52musharib.api.LoadingState
import com.pocket52musharib.api.Resource
import com.pocket52musharib.api.model.UserInfo
import com.pocket52musharib.api.model.UserPost
import com.pocket52musharib.database.UserListDao
import com.pocket52musharib.ui.Repository.UserListRep
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserListViewModel @ViewModelInject constructor(private val repo:UserListRep,private val localDataSource:UserListDao) : ViewModel() {
    private val _dataValue = MutableLiveData<List<UserInfo>>()
    private val _afterLoadValue = MutableLiveData<List<UserInfo>>()
    val data: LiveData<List<UserInfo>> get()  = _dataValue
    private val _loading = MutableLiveData<LoadingState>()
    val loading: LiveData<LoadingState> get() = _loading
    private val _noDataFound = MutableLiveData<Boolean>()
    val noDataFound: LiveData<Boolean> get() = _noDataFound

    private val _dataPostValue = MutableLiveData<List<UserPost>>()
    val dataPost: LiveData<List<UserPost>> get()  = _dataPostValue

    fun loadData(lifecycleOwner: LifecycleOwner){
        repo.getUserListLocal().observe(lifecycleOwner,{
            when (it.status) {
                Resource.Status.LOADING -> {
                    _loading.postValue(LoadingState.LOADING)
                }
                Resource.Status.SUCCESS -> {
                    if (it.data?.isNotEmpty() == true){
                        _dataValue.postValue(it.data)
                        _afterLoadValue.postValue(it.data)
                        _noDataFound.postValue(false)
                    }else{
                        _noDataFound.postValue(true)
                    }

                    _loading.postValue(LoadingState.LOADED)
                }
                Resource.Status.ERROR -> {
                    _loading.postValue(LoadingState.error(it.message))
                }
            }
        })
    }
    fun search(lifecycleOwner: LifecycleOwner,query:String){
        if (query.isNotBlank() && query.isNotEmpty()){
                viewModelScope.launch {
                   localDataSource.getUser(query).observe(lifecycleOwner,{
                       if (it.isNullOrEmpty())  _noDataFound.postValue(true) else _dataValue.postValue(it)

                   })

                }

       } else{
           _dataValue.postValue(_afterLoadValue.value)
           _noDataFound.postValue(false)
       }
    }

   fun loadUserPost(lifecycleOwner: LifecycleOwner,userId:String = ""){
       repo.getUserListLocalPost(userId).observe(lifecycleOwner,{
           when (it.status) {
               Resource.Status.LOADING -> {
                   _loading.postValue(LoadingState.LOADING)
               }
               Resource.Status.SUCCESS -> {
                   if (it.data?.isNotEmpty() == true){
                       _dataPostValue.postValue(it.data)
                       _noDataFound.postValue(false)
                   }else{
                       _noDataFound.postValue(true)
                   }

                   _loading.postValue(LoadingState.LOADED)
               }
               Resource.Status.ERROR -> {
                   _loading.postValue(LoadingState.error(it.message))
               }
           }
       })
   }


}