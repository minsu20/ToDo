package com.example.todo.domain.user.presentation.dto.res

import com.example.todo.global.dto.TokenInfoResponse

object UserResponse {
    data class SignupResponse(val id:Long)

    data class LoginResponse(
            val id:Long,
            val accessToken: String,
            val refreshToken: String)
    {
        companion object {
            fun from(tokenInfoResponse: TokenInfoResponse): LoginResponse {
                return LoginResponse(
                        id=tokenInfoResponse.userId,
                        accessToken = tokenInfoResponse.accessToken,
                        refreshToken = tokenInfoResponse.refreshToken,
                )
            }
        }
    }
}