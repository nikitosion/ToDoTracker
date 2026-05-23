package com.memowave.todotracker.data

data class TaskEntity(
    val id: Long,
    val title: String,
    val isDone: Boolean
)