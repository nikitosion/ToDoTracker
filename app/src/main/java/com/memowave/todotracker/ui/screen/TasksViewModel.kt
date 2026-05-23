package com.memowave.todotracker.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.memowave.todotracker.data.FakeRepository
import com.memowave.todotracker.domain.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TasksViewModel(private val tasksRepository: FakeRepository) : ViewModel() {

    private val _state = MutableStateFlow(TasksState(isLoading = true))
    val state: StateFlow<TasksState> = _state

    fun load() {
        viewModelScope.launch {
            val tasksList = tasksRepository.getTasks().map { taskEntity ->
                Task(
                    id = taskEntity.id,
                    title = taskEntity.title,
                    isDone = taskEntity.isDone
                )
            }
            _state.update { currentState ->
                currentState.copy(tasks = tasksList, isLoading = false)
            }
        }
    }

    fun getTaskById(taskId: Long): Task? {
        return state.value.tasks.firstOrNull { it.id == taskId }
    }

    fun onQueryChanged(newQuery: String) {
        _state.update { currentState ->
            currentState.copy(query = newQuery)
        }
    }

    fun saveTask(title: String, id: Long? = null) {
        viewModelScope.launch {
            val updatedTask = tasksRepository.updateTask(id ?: -1, title)

            if (updatedTask == null) {
                tasksRepository.addTask(title)
            }
            val updatedTasksList = tasksRepository.getTasks().map { taskEntity ->
                Task(
                    id = taskEntity.id,
                    title = taskEntity.title,
                    isDone = taskEntity.isDone
                )
            }
            _state.update { currentState ->
                currentState.copy(tasks = updatedTasksList)
            }
        }
    }

    fun onTaskToggle(taskId: Long, invertedDone: Boolean) {
        viewModelScope.launch {
            tasksRepository.toggleDone(taskId, invertedDone)
            val newTasksList = tasksRepository.getTasks().map { taskEntity ->
                Task(
                    id = taskEntity.id,
                    title = taskEntity.title,
                    isDone = taskEntity.isDone
                )
            }
            _state.update { currentState ->
                currentState.copy(tasks = newTasksList, isLoading = false)
            }
        }
    }
}