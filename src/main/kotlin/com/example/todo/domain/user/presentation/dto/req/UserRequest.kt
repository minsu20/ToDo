package com.example.todo.domain.user.presentation.dto.req

object UserRequest {
    data class SignupRequest(
            val email: String,
            val nickName: String,
            val password: String
    )

    data class LoginRequest(
            val email: String,
            val password: String
    )
}