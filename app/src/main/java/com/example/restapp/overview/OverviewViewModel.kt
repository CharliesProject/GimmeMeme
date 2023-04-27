package com.example.restapp.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restapp.JsonObjectFewMemes
import com.example.restapp.network.MyApi
import com.example.restapp.network.SinglePhoto
import kotlinx.coroutines.launch


enum class MyApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel : ViewModel() {

    private val _status = MutableLiveData<MyApiStatus>()
    val status: LiveData<MyApiStatus> = _status

/*    private val _photos = MutableLiveData<List<SinglePhoto>>()
    val photos: LiveData<List<SinglePhoto>> = _photos*/

    private val _jsonObj = MutableLiveData<JsonObjectFewMemes>()
    val jsonObj: LiveData<JsonObjectFewMemes> = _jsonObj

    private val _photos = MutableLiveData<List<SinglePhoto>>()
    val photos: LiveData<List<SinglePhoto>> = _photos

    private val _photoMeme = MutableLiveData<List<String>>()
    val photoMeme: LiveData<List<String>> = _photoMeme


    init {
        getMyPhotos()
    }

     fun getMyPhotos() {
        viewModelScope.launch {
            _status.value = MyApiStatus.LOADING
            try {
            //    _photos.value = MyApi.retrofitService.getPhotos()
                _jsonObj.value = MyApi.retrofitService.getPhotos()
                _photos.value = _jsonObj.value!!.memes

            //    _photoMeme.value = _photos.value?.imgSrcUrl

                _status.value = MyApiStatus.DONE
            } catch (e: Exception) {
                _status.value = MyApiStatus.ERROR
                _photos.value = listOf()
            }
        }
    }
}