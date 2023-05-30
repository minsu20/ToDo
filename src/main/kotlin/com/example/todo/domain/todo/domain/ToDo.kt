package com.example.todo.domain.todo.domain

import com.example.todo.domain.user.domain.User
import com.example.todo.global.entity.BaseEntity
import lombok.RequiredArgsConstructor
import java.time.LocalDate
import javax.persistence.*

@Entity
@RequiredArgsConstructor
@Table(name = "todo")
data class ToDo (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "todo_id")
        val id: Long?=null,

        @Column(nullable = false)
        var content: String,

        @Column(nullable = false)
        var whenToDo: LocalDate,

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        var status: ToDoStatus = ToDoStatus.TODO,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", nullable = false)
        var user: User

) : BaseEntity()
