package com.example.testwalmart.repository

import com.example.testwalmart.model.Country
import com.example.testwalmart.network.RetrofitClient

class CountryRepository {
    suspend fun fetchCountries(): Result<List<Country>> {
        return try {
            val response = RetrofitClient.apiService.getCountries()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}