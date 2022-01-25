package com.example.treker_fefu.api

sealed class MyResult<T> {
    class Success<T>(val result: T): MyResult<T>()
    class Error<T>(val e: Throwable): MyResult<T>()
}
