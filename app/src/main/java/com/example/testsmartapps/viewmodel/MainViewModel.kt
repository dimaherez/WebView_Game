package com.example.testsmartapps.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.testsmartapps.repository.Repository

class MainViewModel: ViewModel() {

    var response: LiveData<Boolean> = Repository.getResponseLiveData()


}