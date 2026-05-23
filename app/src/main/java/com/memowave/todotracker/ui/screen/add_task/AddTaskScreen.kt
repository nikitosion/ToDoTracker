package com.memowave.todotracker.ui.screen.add_task

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.memowave.todotracker.domain.Task
import com.memowave.todotracker.ui.screen.TasksViewModel

@Composable
fun AddTaskRoute(id: Long?, viewModel: TasksViewModel, onBack: () -> Unit) {
    var task by remember { mutableStateOf<Task?>(null) }

    if (id != null) {
        LaunchedEffect(id) {
            task = viewModel.getTaskById(id)
        }
    }

    AddTaskScreen(
        task = task,
        onSaved = viewModel::saveTask,
        onBack = onBack
    )
}

@Composable
fun AddTaskScreen(
    modifier: Modifier = Modifier,
    task: Task?,
    onSaved: (String, Long?) -> Unit,
    onBack: () -> Unit
) {
    var inputTitle by remember (task) { mutableStateOf(task?.title ?: "") }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "НАЗВАНИЕ ЗАДАЧИ",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            OutlinedTextField(
                modifier = modifier.fillMaxWidth(),
                value = inputTitle,
                placeholder = { Text(text = "Введите название задачи...") },
                label = { Text(text = "Название") },
                shape = RoundedCornerShape(24.dp),
                onValueChange = {
                    inputTitle = it
                },
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.BottomEnd),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                modifier = Modifier,
                onClick = {
                    onBack()
                }
            ) {
                Text(text = "Назад")
            }

            Button(
                modifier = Modifier,
                onClick = {
                    onSaved(
                        inputTitle,
                        task?.id
                    )
                    onBack()
                }
            ) {
                Text(text = "Сохранить")
            }
        }
    }
}

@Composable
@Preview
fun AddTaskScreenPreview() {
    AddTaskScreen(
        task = Task(
            id = 1L,
            title = "Пример задачи",
            isDone = false
        ),
        onSaved = { _, _ -> },
        onBack = {}
    )
}