package com.example.todo.global.dto

data class TokenInfoResponse(
        val grantType: String,
        val accessToken: String,
        val refreshToken: String,
        val refreshTokenExpirationTime: Long
) {
    companion object {
        fun from(
                grantType: String,
                accessToken: String,
                refreshToken: String,
                refreshTokenExpirationTime: Long
        ) = TokenInfoResponse(
                grantType = grantType,
                accessToken = accessToken,
                refreshToken = refreshToken,
                refreshTokenExpirationTime = refreshTokenExpirationTime
        )
    }
}
