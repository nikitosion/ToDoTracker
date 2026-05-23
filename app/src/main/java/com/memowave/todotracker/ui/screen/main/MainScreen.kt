package com.memowave.todotracker.ui.screen.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.memowave.todotracker.R
import com.memowave.todotracker.ui.screen.SearchBar
import com.memowave.todotracker.ui.screen.TasksState
import com.memowave.todotracker.ui.screen.TasksViewModel
import com.memowave.todotracker.domain.Task as TaskItem

@Composable
fun MainScreenRoute(viewModel: TasksViewModel = viewModel(), onTaskClick: (Long) -> Unit, onSettingsClick: () -> Unit) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.load()
    }

    MainScreen(
        state = state,
        toggleTask = { taskId, invertedDone ->
            viewModel.onTaskToggle(taskId, invertedDone)
        },
        onQueryChanged = viewModel::onQueryChanged,
        onTaskClick = onTaskClick,
        onSettingsClick = onSettingsClick
    )
}

@Composable
fun MainScreen(
    state: TasksState,
    onQueryChanged: (String) -> Unit,
    toggleTask: (Long, Boolean) -> Unit,
    onTaskClick: (Long) -> Unit,
    onSettingsClick: () -> Unit
) {

    val filteredTasks = state.tasks.filter { it.title.contains(state.query, ignoreCase = true) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 16.dp,
                        top = 32.dp,
                        end = 16.dp,
                        bottom = 16.dp
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Your tasks",
                    style = MaterialTheme.typography.displayMedium
                )

                OutlinedIconButton(
                    onClick = {
                        onSettingsClick()
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.round_settings_24),
                        contentDescription = "Settings button"
                    )
                }
            }


            SearchBar(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 24.dp),
                value = state.query,
                onValueChanged = onQueryChanged,
            )


            when {
                state.isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                state.tasks.isEmpty() -> {
                    Text(
                        modifier = Modifier.padding(horizontal = 16.dp).align(Alignment.CenterHorizontally),
                        text = "No tasks for today! Enjoy your free time :)",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(filteredTasks) { task ->
                            TaskItem(
                                id = task.id,
                                title = task.title,
                                isTaskDone = task.isDone,
                                onTaskToggle = { id, invertedDone -> toggleTask(id, invertedDone) },
                                onClick = { onTaskClick(task.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, name = "Main — with tasks")
fun MainScreenPreview() {
    MainScreen(
        state = TasksState(
            tasks = listOf(
                TaskItem(id = 1, title = "Buy milk", isDone = false),
                TaskItem(id = 2, title = "Walk the dog", isDone = false),
                TaskItem(id = 3, title = "Write quarterly report", isDone = false),
                TaskItem(id = 4, title = "Call mom", isDone = true),
            )
        ),
        onQueryChanged = { },
        toggleTask = { _, _ -> },
        onTaskClick = { },
        onSettingsClick = { }
    )
}

@Composable
@Preview(showBackground = true, name = "Main — empty")
fun MainScreenEmptyPreview() {
    MainScreen(
        state = TasksState(tasks = emptyList()),
        onQueryChanged = { },
        toggleTask = { _, _ -> },
        onTaskClick = { },
        onSettingsClick = { }
    )
}

@Composable
@Preview(showBackground = true, name = "Main — empty")
fun MainScreenLoadingPreview() {
    MainScreen(
        state = TasksState(tasks = emptyList(), isLoading = true),
        onQueryChanged = { },
        toggleTask = { _, _ -> },
        onTaskClick = { },
        onSettingsClick = {}
    )
}
