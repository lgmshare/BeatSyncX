package com.changhong.beatsyncx.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.changhong.beatsyncx.databases.AppDatabase
import com.changhong.beatsyncx.models.Heartbeat
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _listData = MutableLiveData<List<Heartbeat>>().apply {
        value = arrayListOf<Heartbeat>()
    }
    val listLiveData: LiveData<List<Heartbeat>> = _listData

    fun loadData() {
        viewModelScope.launch {
            val list = AppDatabase.getInstance().dataDao().queryAll()
            _listData.value = list
        }
    }
}