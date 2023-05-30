package com.example.todo.global.exception.user

import com.example.todo.global.exception.user.UserExceptionList.NOT_HAVE_EMAIL_ERROR

class NotFoundEmailException : UserException(
        NOT_HAVE_EMAIL_ERROR.errorCode,
        NOT_HAVE_EMAIL_ERROR.httpStatus,
        NOT_HAVE_EMAIL_ERROR.message
)