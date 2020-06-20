package com.example.nikecodingchallenge.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nikecodingchallenge.model.UrbanDictionaryResponse
import com.example.nikecodingchallenge.network.PostApi
import com.example.nikecodingchallenge.network.Result
import kotlinx.coroutines.launch

class AppViewModel: ViewModel() {

    val searchResultSuccess = MutableLiveData<UrbanDictionaryResponse>()
    val searchResultFailure = MutableLiveData<UrbanDictionaryResponse>()

    fun getSearchResult(searchTerm: String){

        viewModelScope.launch {

            val searchItem = PostApi.postApi.searchTerm(searchTerm)

            when (searchItem) {

                is Result.Success<*> ->{

                    searchResultSuccess.postValue(searchItem.data as UrbanDictionaryResponse)

                }

                is Result.Error<*> ->{

                    searchResultFailure.postValue(searchItem.exception as UrbanDictionaryResponse)
                }
            }
        }
    }
}

