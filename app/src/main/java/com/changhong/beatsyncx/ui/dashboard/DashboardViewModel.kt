package com.changhong.beatsyncx.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {

    private val _listLiveData = MutableLiveData<List<String>>().apply {
        value = arrayListOf()
    }

    val listLiveData: LiveData<List<String>> = _listLiveData

    fun loadData() {
        viewModelScope.launch {
        }
    }
}