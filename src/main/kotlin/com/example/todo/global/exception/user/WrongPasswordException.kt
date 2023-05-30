package com.example.todo.global.exception.user

class WrongPasswordException : UserException(
        UserExceptionList.WRONG_PASSWORD_ERROR.errorCode,
        UserExceptionList.WRONG_PASSWORD_ERROR.httpStatus,
        UserExceptionList.WRONG_PASSWORD_ERROR.message
)