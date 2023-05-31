package com.example.todo.domain.todo.domain

import org.springframework.data.jpa.repository.JpaRepository

interface ToDoRepository : JpaRepository<ToDo, Long>, ToDoCustomRepository {
}