package com.example.todo.global.exception.user

class OverlapUserException : UserException(
        UserExceptionList.OVERLAP_USER.errorCode,
        UserExceptionList.OVERLAP_USER.httpStatus,
        UserExceptionList.OVERLAP_USER.message
)