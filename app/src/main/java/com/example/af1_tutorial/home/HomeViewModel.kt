package com.example.af1_tutorial.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.af1_tutorial.data.model.User
import com.example.af1_tutorial.data.remote.response.GetUserResponse
import com.example.af1_tutorial.data.remote.service.ApiConfig
import com.example.af1_tutorial.tools.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel: ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _userList = MutableLiveData<List<User>>()
    val userList: LiveData<List<User>> = _userList

    private val _isEmpty = MutableLiveData<Boolean>()
    val isEmpty: LiveData<Boolean> = _isEmpty

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _isError

    init {
        getUsers(1)
    }

    fun getUsers(page: Int) {
        _isLoading.value = true
        _isEmpty.value = false
        _isError.value = false
        val client = ApiConfig.getApiService().getUsers(page)
        client.enqueue(object : Callback<GetUserResponse> {
            override fun onResponse(
                call: Call<GetUserResponse>,
                response: Response<GetUserResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.let {
                        if (it.data.isEmpty()) {
                            _isEmpty.value = page == 1
                        } else {
                            _userList.value = it.data
                        }
                    }
                } else {
                    _isError.value = true
                }
            }

            override fun onFailure(call: Call<GetUserResponse>, t: Throwable) {
                _isLoading.value = false
                _isError.value = true
            }
        })
    }

}