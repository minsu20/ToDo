package com.example.todo.domain.user.presentation.constant

enum class EUserResponseMessage(
        val message: String
) {
    LOGIN_SUCCESS("로그인을 했습니다"),
    SIGN_UP_SUCCESS("회원 가입을 완료했습니다")
}