package com.example.todo.global.exception.todo

class NotFoundToDoByIdException : ToDoException(
        ToDoExceptionList.NOT_FOUND_TODO_BY_ID.errorCode,
        ToDoExceptionList.NOT_FOUND_TODO_BY_ID.httpStatus,
        ToDoExceptionList.NOT_FOUND_TODO_BY_ID.message
)