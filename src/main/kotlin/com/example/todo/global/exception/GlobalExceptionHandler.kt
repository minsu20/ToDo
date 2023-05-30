package com.example.todo.global.exception

import com.example.todo.global.dto.ErrorResponse
import lombok.extern.slf4j.Slf4j
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.regex.PatternSyntaxException
import java.util.stream.Collectors

@RestControllerAdvice
@Slf4j
class GlobalExceptionHandler {
    @ExceptionHandler(ApplicationException::class)
    fun handleApplicationException(ex: ApplicationException): ResponseEntity<ErrorResponse> {
        log.warn(LOG_FORMAT, ex.javaClass.simpleName, ex.errorCode, ex.message)
        return ResponseEntity.status(ex.httpStatus).body<ErrorResponse>(ErrorResponse(ex.errorCode, ex.message))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidException(ex: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        val errorCode = "400"
        log.warn(LOG_FORMAT, ex.javaClass.simpleName, errorCode, ex.message)
        val message = ex.bindingResult.fieldErrors.stream()
                .map { obj: FieldError -> obj.defaultMessage }
                .collect(Collectors.joining(", "))
        val errorResponse = ErrorResponse(errorCode, message)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(errorResponse)
    }

    @ExceptionHandler(PatternSyntaxException::class)
    fun handlePatternSyntaxException(ex: PatternSyntaxException): ResponseEntity<ErrorResponse> {
        val errorCode = "400"
        val message = "올바른 패턴을 입력하세요. "
        log.warn(LOG_FORMAT, ex.javaClass.simpleName, errorCode, ex.message)
        val errorResponse = ErrorResponse(errorCode, message)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(errorResponse)
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun handleHttpRequestMethodNotSupportedException(ex: HttpRequestMethodNotSupportedException): ResponseEntity<ErrorResponse> {
        val errorCode = "405"
        val message = "클라이언트가 사용한 HTTP 메서드가 리소스에서 허용되지 않습니다."
        log.warn(LOG_FORMAT, ex.javaClass.simpleName, errorCode, ex.message)
        val errorResponse = ErrorResponse(errorCode, message)
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED.value()).body(errorResponse)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception): ResponseEntity<ErrorResponse> {
        val errorCode = "500"
        val message = "서버에서 요청을 처리하는 동안 오류가 발생했습니다."
        log.warn(LOG_FORMAT, ex.javaClass.simpleName, errorCode, ex.message)
        val errorResponse = ErrorResponse(errorCode, ex.javaClass.simpleName + ":" + message)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(errorResponse)
    }

    companion object {
        private val log = KotlinLogging.logger {}
        private const val LOG_FORMAT = "Class : {}, Code : {}, Message : {}"
    }
}


