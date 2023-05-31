package com.example.todo.domain.todo.presentation.constant

enum class ETodoResponseMessage (val message: String
) {
    CREATE_TODO_SUCCESS("투두를 생성했습니다"),
    UPDATE_TODO_SUCCESS("투두를 수정했습니다"),
    CHANGE_TODO_STATUS_SUCCESS("투두상태를 수정했습니다"),
    GET_TODO_ASC_SUCCESS("투두를 일정 예정 날짜순-오름차순으로 조회했습니다"),
    GET_TODO_DESC_SUCCESS("투두를 일정 예정 날짜순-내림차순으로 조회했습니다")
}