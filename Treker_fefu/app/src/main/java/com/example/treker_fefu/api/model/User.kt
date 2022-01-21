package com.example.treker_fefu.api.model

data class User(
    val id: Long,
    val name: String,
    val login: String,
    val gender: Gender
)
