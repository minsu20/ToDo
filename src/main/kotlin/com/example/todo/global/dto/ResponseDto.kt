package com.example.todo.global.dto

import com.fasterxml.jackson.annotation.JsonInclude

data class ResponseDto<T>(
        val statusCode: Int,
        val message: String,
        val data: T? = null
) {
    companion object {
        fun <T> create(statusCode: Int, message: String, data: T? = null): ResponseDto<T> =
                ResponseDto(statusCode, message, data)
    }
}
