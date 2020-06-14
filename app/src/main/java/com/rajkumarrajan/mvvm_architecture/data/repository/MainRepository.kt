package com.rajkumarrajan.mvvm_architecture.data.repository

import com.rajkumarrajan.mvvm_architecture.data.api.ApiService
import com.rajkumarrajan.mvvm_architecture.data.model.User
import io.reactivex.Single
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService) {

    fun getUsers(): Single<List<User>> {
        return apiService.getUsers()
    }

}