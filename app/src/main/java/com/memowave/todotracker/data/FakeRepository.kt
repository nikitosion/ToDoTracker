package com.memowave.todotracker.data

import kotlinx.coroutines.delay

class FakeRepository {
    private val tasks = mutableListOf(
        TaskEntity(1, "Clean home", false),
        TaskEntity(2, "Do job", false),
        TaskEntity(3, "Get asleep", false),
        TaskEntity(4, "Go to the gym", false),
        TaskEntity(5, "Go to the shop", false),
        TaskEntity(6, "Go to the park", false),
        TaskEntity(7, "Go to the cinema", false),
        TaskEntity(8, "Go to the restaurant", false),
        TaskEntity(9, "Go to the cafe", false),
        TaskEntity(10, "Go to the bar", false),
    )


    suspend fun getTasks(): List<TaskEntity> {
        delay(500)
        return tasks
    }

    suspend fun getTask(taskId: Long): TaskEntity? {
        delay(500)
        return tasks.firstOrNull { it.id == taskId }
    }

    suspend fun toggleDone(taskId: Long, isDone: Boolean): TaskEntity? {
        delay(500)
        val taskInd = tasks.indexOfFirst { it.id == taskId }
        if (taskInd < 0) {
            return null
        }

        val updatedTask = tasks[taskInd].copy(isDone = isDone)
        tasks[taskInd] = updatedTask
        return updatedTask
    }
}