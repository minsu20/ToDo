package com.example.todo.global.exception.user

class NotFoundPasswordException : UserException(
        UserExceptionList.NOT_PASSWORD_ERROR.errorCode,
        UserExceptionList.NOT_PASSWORD_ERROR.httpStatus,
        UserExceptionList.NOT_PASSWORD_ERROR.message
)