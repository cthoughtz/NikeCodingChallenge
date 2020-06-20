package com.example.nikecodingchallenge.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nikecodingchallenge.model.UrbanDictionaryResponse
import com.example.nikecodingchallenge.network.PostApi
import kotlinx.coroutines.launch

class AppViewModel: ViewModel() {

    var searchResultSuccess = MutableLiveData<UrbanDictionaryResponse>()
    var searchResultFailure = MutableLiveData<UrbanDictionaryResponse>()

    fun getSearchResult(searchTerm: String){

        viewModelScope.launch {

            val searchItem = PostApi.postApi.searchTerm(searchTerm).body()
            searchResultSuccess.postValue(searchItem)

        }
    }
}

