package com.example.restapp.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restapp.network.MyApi
import com.example.restapp.network.SinglePhoto
import kotlinx.coroutines.launch
import java.io.IOException
import retrofit2.HttpException

enum class MyApiStatus { START, LOADING, ERROR, EMPTY, DONE, UNKNOWN }

class OverviewViewModel : ViewModel() {

    private val _status = MutableLiveData<MyApiStatus>()
    val status: LiveData<MyApiStatus> = _status

    private val _jsonObj = MutableLiveData<SinglePhoto>()
    val jsonObj: LiveData<SinglePhoto> = _jsonObj

    private val _emptyPhoto = SinglePhoto(
        postLink = "",
        subreddit = "",
        title = "",
        imgSrcUrl = "",
        nsfw = false,
        spoiler = false,
        author = "",
        ups = "",
        preview = emptyList()
    )
    val emptyPhoto get() = _emptyPhoto


    init {
        startPhoto()
    }

    fun startPhoto() {
        viewModelScope.launch {
            _status.value = MyApiStatus.START
        }
    }

    fun setStatusLoading() {
        viewModelScope.launch {
            _status.value = MyApiStatus.LOADING
        }
    }

    fun deletePhoto() {
        _jsonObj.value = _emptyPhoto
    }

    fun getMyPhotos() {
        deletePhoto()
        setStatusLoading()
        viewModelScope.launch {
            try {
                _jsonObj.value = MyApi.retrofitService.getPhotos()
                _status.value = MyApiStatus.DONE
            } catch (e: IOException) {
                _status.value = MyApiStatus.ERROR
                _jsonObj.value = _emptyPhoto
            } catch (e: HttpException) {
                _status.value = MyApiStatus.EMPTY
                _jsonObj.value = _emptyPhoto
            } catch (e: Exception) {
                _status.value = MyApiStatus.UNKNOWN
                _jsonObj.value = _emptyPhoto
            }
        }
    }

}