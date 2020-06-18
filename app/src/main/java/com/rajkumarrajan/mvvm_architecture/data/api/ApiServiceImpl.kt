package com.rajkumarrajan.mvvm_architecture.data.api

import com.rajkumarrajan.mvvm_architecture.data.model.User
import io.reactivex.Single

class ApiServiceImpl : ApiService {
    private val apiService = RetrofitInstance.createService(ApiService::class.java)
    override fun getUsers(): Single<List<User>> {
        return apiService.getUsers()
    }
}