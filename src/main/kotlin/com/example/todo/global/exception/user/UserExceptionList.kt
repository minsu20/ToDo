package com.example.todo.global.exception.user

import org.springframework.http.HttpStatus

enum class UserExceptionList(
        val errorCode: String,
        val httpStatus: HttpStatus,
        val message: String
) {

    NOT_HAVE_EMAIL_ERROR("U0001", HttpStatus.NOT_FOUND, "해당 email로 User를 찾을 수 없습니다."),
    OVERLAP_USER("U0002", HttpStatus.BAD_REQUEST, "이미 존재하는 회원입니다"),
    NOT_PASSWORD_ERROR("U0003", HttpStatus.NOT_FOUND, "비밀번호가 잘못되었습니다")
}