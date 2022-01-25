package com.example.treker_fefu.mainscreens.fragmentscreens.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.treker_fefu.api.ApiInterface
import com.example.treker_fefu.api.MyResult
import com.example.treker_fefu.api.model.Token
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SiginViewModel : ViewModel() {
    private val loginRepository = ApiInterface()

    private val _dataFlow = MutableSharedFlow<MyResult<Token>>(replay = 0)

    val dataFlow get() = _dataFlow

    fun login(login:String, password:String) {
        viewModelScope.launch {
            loginRepository.login(login, password)
                .collect {
                    when(it) {
                        is MyResult.Success<*> -> _dataFlow.emit(it)
                        is MyResult.Error<*> -> _dataFlow.emit(it)
                    }
                }
        }
    }
}