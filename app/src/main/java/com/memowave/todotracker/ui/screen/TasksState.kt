package com.memowave.todotracker.ui.screen

import com.memowave.todotracker.domain.Task

data class TasksState (
    val tasks: List<Task> = emptyList(),
    val query: String = "",
    val isLoading: Boolean = false
)