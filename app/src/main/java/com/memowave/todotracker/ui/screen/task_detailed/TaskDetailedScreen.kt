package com.memowave.todotracker.ui.screen.task_detailed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.memowave.todotracker.R
import com.memowave.todotracker.domain.Task
import com.memowave.todotracker.ui.screen.TasksViewModel

@Composable
fun TaskDetailedRoute(id: Long, tasksViewModel: TasksViewModel, onBack: () -> Unit) {

    var task by remember { mutableStateOf<Task?>(null) }

    LaunchedEffect(task?.isDone) {
        task = tasksViewModel.getTaskById(id)
    }

    TaskDetailedScreen(task, onBack, onTaskToggle = tasksViewModel::onTaskToggle)
}

@Composable
fun TaskDetailedScreen(
    task: Task?,
    onBack: () -> Unit = {},
    onTaskToggle: (Long, Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (task == null) {
            Text(text = "Task not found")
        } else {
            Text(
                text = task.title,
                style = MaterialTheme.typography.displaySmall
            )
            Button(
                modifier = Modifier.padding(top = 32.dp),
                onClick = { onTaskToggle(task.id, !task.isDone) }
            ) {
                Icon(
                    modifier = Modifier.padding(end = 8.dp),
                    painter = if (task.isDone) {
                        painterResource(id = R.drawable.round_remove_done_24)
                    } else {
                        painterResource(id = R.drawable.round_done_24)
                    },
                    contentDescription = "Toggle done"
                )
                Text(text = "Mark ${if (task.isDone) "undone" else "done"}")
            }
            Button(
                modifier = Modifier.padding(top = 4.dp),
                onClick = { onBack() }
            ) {
                Icon(
                    modifier = Modifier.padding(end = 8.dp),
                    painter = painterResource(id = R.drawable.round_arrow_back_24),
                    contentDescription = "Toggle done"
                )
                Text(text = "Go back")
            }
        }
    }
}

@Composable
@Preview
fun TaskDetailedScreenPreview() {
    val task = Task(
        id = 1L,
        title = "Task title",
        isDone = true
    )
    TaskDetailedScreen(task, onBack = {}, onTaskToggle = { _, _ -> })
}