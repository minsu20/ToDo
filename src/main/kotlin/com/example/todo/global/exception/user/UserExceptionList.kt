package com.example.todo.global.exception.user

import org.springframework.http.HttpStatus

enum class UserExceptionList(
        val errorCode: String,
        val httpStatus: HttpStatus,
        val message: String
) {

    NOT_HAVE_EMAIL_ERROR("U0001", HttpStatus.NOT_FOUND, "해당 email로 User를 찾을 수 없습니다."),
    OVERLAP_USER("U0002", HttpStatus.CONFLICT, "회원가입 실패: 이미 존재하는 회원입니다"),
    WRONG_PASSWORD_ERROR("U0003", HttpStatus.BAD_REQUEST, "로그인 실패: 비밀번호가 잘못되었습니다"),
    WRONG_EMAIl_ERROR("U0004", HttpStatus.NOT_FOUND, "로그인 실패: 이메일이 잘못되었습니다")
}