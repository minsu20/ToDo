package com.example.todo.global.exception.user

class WrongEmailException : UserException(
        UserExceptionList.WRONG_EMAIl_ERROR.errorCode,
        UserExceptionList.WRONG_EMAIl_ERROR.httpStatus,
        UserExceptionList.WRONG_EMAIl_ERROR.message
)