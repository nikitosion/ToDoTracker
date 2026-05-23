package com.memowave.todotracker.domain

data class Task(
    val id: Long,
    val title: String,
    val isDone: Boolean
)
