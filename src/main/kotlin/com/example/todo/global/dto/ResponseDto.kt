package com.example.todo.global.dto

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ResponseDto<T>(
        val statusCode: Int,
        val message: String,
        val data: T? = null
) {
    companion object {
        fun <T> create(statusCode: Int, message: String): ResponseDto<T> {
            return ResponseDto(statusCode, message, null)
        }

        fun <T> create(statusCode: Int, message: String, data: T): ResponseDto<T> {
            return ResponseDto(statusCode, message, data)
        }
    }
}