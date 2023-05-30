package com.example.todo.global.config.security.exception

class LoggedInUserNotFoundException : SecurityException (
        SecurityExceptionList.LOGGED_IN_NOT_FOUND.errorCode,
        SecurityExceptionList.LOGGED_IN_NOT_FOUND.httpStatus,
        SecurityExceptionList.LOGGED_IN_NOT_FOUND.message
)