package com.example.testsmartapps.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testsmartapps.retrofit.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object Repository {
    private val liveData: MutableLiveData<Boolean> = MutableLiveData()

    fun getResponseLiveData(): LiveData<Boolean> {
        GlobalScope.launch(Dispatchers.IO) { liveData.postValue(RetrofitInstance.api.getResponse()) }
        return liveData
    }
}