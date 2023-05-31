package com.example.todo.domain.todo.presentation.dto.res

import com.example.todo.domain.todo.domain.ToDoStatus
import java.time.LocalDate

object ToDoResponse {
    data class CreateResponse(
            val id: Long
    )
    data class UpdateResponse(
            val id: Long
    )

    data class ChangeStatusResponse(
            val id:Long
    )

    data class GetResponse(
            var id:Long,
            var content:String,
            var whenToDo: LocalDate,
            var status: ToDoStatus,
            var nickName: String,
    )
}