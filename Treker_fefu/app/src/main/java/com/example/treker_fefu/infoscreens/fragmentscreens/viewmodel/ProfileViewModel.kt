package com.example.treker_fefu.infoscreens.fragmentscreens.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.treker_fefu.api.ApiInterface
import com.example.treker_fefu.api.MyResult
import com.example.treker_fefu.api.model.User
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val loginRepository = ApiInterface()

    private val _profile = MutableSharedFlow<MyResult<User>>(replay = 0)
    private val _logoutUser = MutableSharedFlow<MyResult<Unit>>(replay = 0)

    val profile get() = _profile
    val logoutUser get() = _logoutUser

    fun getProfile() {
        viewModelScope.launch {
            loginRepository.getProfile().collect {
                when (it) {
                        is MyResult.Success<*> -> _profile.emit(it)
                        is MyResult.Error<*> -> _profile.emit(it)

                }
            }

        }
    }


    fun logout() {
        viewModelScope.launch {
            loginRepository.logout()
                .collect {
                    when (it) {
                        is MyResult.Success<*> -> _logoutUser.emit(it)
                        is MyResult.Error<*> -> _logoutUser.emit(it)
                    }
                }
        }
    }
}
