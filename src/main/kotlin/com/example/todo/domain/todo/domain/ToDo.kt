package com.example.todo.domain.todo.domain

import com.example.todo.domain.user.domain.User
import com.example.todo.global.entity.BaseEntity
import lombok.RequiredArgsConstructor
import java.time.LocalDate
import javax.persistence.*

@Entity
@RequiredArgsConstructor
@Table(name = "todo")
class ToDo (
        content: String,
        whenToDo: LocalDate,
        user:User,
        nickName: String
) : BaseEntity() {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "todo_id")
        val id: Long? = null

        @Column(nullable = false)
        var content: String=content

        @Column(nullable = false)
        var whenToDo: LocalDate=whenToDo

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        var status: ToDoStatus = ToDoStatus.TODO

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", nullable = false)
        var user: User=user

        @Column(nullable = false)
        var userNickName: String=nickName

        fun changeStatus() {
                status = when (status) {
                        ToDoStatus.TODO -> ToDoStatus.DONE
                        ToDoStatus.DONE -> ToDoStatus.TODO
                }
        }

}
