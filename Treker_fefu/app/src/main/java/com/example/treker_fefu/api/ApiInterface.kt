package com.example.treker_fefu.api

import com.example.treker_fefu.App
import com.example.treker_fefu.api.model.Token
import com.example.treker_fefu.api.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ApiInterface {
    private val activityApi = App.INSTANCE.retrofit.create(API::class.java)

    suspend fun register(login:String, password:String, name:String, gender: Int): Flow<MyResult<Token>> =
        flow<MyResult<Token>> {
            emit(
                MyResult.Success(
                    activityApi.register(login, password, name, gender)
                )
            )
        }
            .catch { emit(MyResult.Error(it)) }
            .flowOn(Dispatchers.IO)

    suspend fun login(login:String, password:String): Flow<MyResult<Token>> =
        flow<MyResult<Token>> {
            emit(
                MyResult.Success(
                    activityApi.login(login, password)
                )
            )
        }
            .catch { emit(MyResult.Error(it)) }
            .flowOn(Dispatchers.IO)

    suspend fun getProfile(): Flow<MyResult<User>> =
        flow<MyResult<User>> {
            emit(
                MyResult.Success(
                    activityApi.getProfile()
                )
            )
        }
            .catch { emit(MyResult.Error(it)) }
            .flowOn(Dispatchers.IO)

    suspend fun logout(): Flow<MyResult<Unit>> =
        flow<MyResult<Unit>> {
            emit(
                MyResult.Success(
                    activityApi.logout()
                )
            )
        }
            .catch { emit(MyResult.Error(it)) }
            .flowOn(Dispatchers.IO)
}