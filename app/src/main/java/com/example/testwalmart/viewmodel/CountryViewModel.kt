package com.example.testwalmart.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testwalmart.model.Country
import com.example.testwalmart.repository.CountryRepository
import kotlinx.coroutines.launch

class CountryViewModel : ViewModel() {
    private val repository = CountryRepository()

    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> = _countries

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            _loading.postValue(true)
            val result = repository.fetchCountries()
            if (result.isSuccess) {
                _countries.postValue(result.getOrDefault(emptyList()))
            } else {
                _error.postValue("Failed to load data.")
            }
            _loading.postValue(false)
        }
    }
}