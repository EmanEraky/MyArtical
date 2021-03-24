package com.eman.articalsapp.presentation.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eman.articalsapp.domain.model.ArticalAll
import com.eman.articalsapp.domain.usecases.getMainArticalUseCase
import com.eman.articalsapp.utils.NetworkHelper
import com.eman.articalsapp.utils.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor
    (val mainRepositoryUseCase: getMainArticalUseCase, val networkHelper: NetworkHelper) :
    ViewModel() {
    private val _artical = MutableLiveData<Resource<ArticalAll>>()

    val articals: MutableLiveData<Resource<ArticalAll>>
        get() = _artical


    fun getArticalResponse(apikey: String) {
        viewModelScope.launch {
            _artical.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepositoryUseCase.getArtical(apikey).collect {
                    _artical.postValue(it)
                }
            } else _artical.postValue(Resource.error("No internet connection", null))
        }
    }


}