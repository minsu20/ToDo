package com.example.todo.domain.todo.domain

import com.example.todo.domain.todo.presentation.dto.req.ToDoRequest.UpdateRequest
import com.example.todo.domain.user.domain.User
import com.example.todo.global.entity.BaseEntity
import lombok.RequiredArgsConstructor
import java.time.LocalDate
import javax.persistence.*

@Entity
@RequiredArgsConstructor
@Table(name = "todo")
class ToDo (
        initialContent: String,
        initialWhenToDo: LocalDate,
        initialUser: User,
        initialNickName: String
) : BaseEntity() {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "todo_id")
        val id: Long? = null

        @Column(nullable = false)
        var content: String=initialContent

        @Column(nullable = false)
        var whenToDo: LocalDate=initialWhenToDo

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        var status: ToDoStatus = ToDoStatus.TODO

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", nullable = false)
        val user: User=initialUser

        @Column(nullable = false)
        val userNickName: String=initialNickName

        fun changeStatus() {
                status = if (status == ToDoStatus.TODO) ToDoStatus.DONE else ToDoStatus.TODO
        }

        fun updateTodo(updateRequest: UpdateRequest) {
                updateRequest.content?.let { this.content = it }
                updateRequest.whenToDo?.let { this.whenToDo = LocalDate.parse(it) }
        }

}
