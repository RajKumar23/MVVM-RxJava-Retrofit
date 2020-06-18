package com.rajkumarrajan.mvvm_architecture.data.api

class ApiHelper(private val apiService: ApiService) {
    fun getUsers() = apiService.getUsers()
}