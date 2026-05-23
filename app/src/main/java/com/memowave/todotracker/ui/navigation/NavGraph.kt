package com.memowave.todotracker.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.memowave.todotracker.data.FakeRepository
import com.memowave.todotracker.ui.screen.TasksViewModel
import com.memowave.todotracker.ui.screen.add_task.AddTaskRoute
import com.memowave.todotracker.ui.screen.main.MainScreenRoute
import com.memowave.todotracker.ui.screen.settings.SettingsScreenRoute
import com.memowave.todotracker.ui.screen.task_detailed.TaskDetailedRoute
import com.memowave.todotracker.ui.theme.ThemeType

sealed class Screen(val route: String) {
    object MainScreen : Screen("main_screen")
    object SettingsScreen : Screen("settings_screen")
    object TaskDetailScreen : Screen("task_detail_screen")
    object AddTaskScreen : Screen("add_task_screen")
}

@Composable
fun NavGraph(
    navController: NavHostController,
    currentTheme: ThemeType,
    onThemeChanged: (ThemeType) -> Unit
) {

    val repository = remember { FakeRepository() }
    val viewModel = remember(repository) { TasksViewModel(repository) }


    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route
    ) {
        composable(route = Screen.SettingsScreen.route) {
            SettingsScreenRoute(
                currentTheme = currentTheme,
                onThemeChange = onThemeChanged
            )
        }

        composable(route = Screen.MainScreen.route) {
            MainScreenRoute(
                viewModel, onTaskClick = { taskId ->
                    navController.navigate("${Screen.TaskDetailScreen.route}/$taskId")
                },
                onSettingsClick = {
                    navController.navigate(Screen.SettingsScreen.route)
                },
                onAddClick = {
                    navController.navigate("${Screen.AddTaskScreen.route}/-1")
                }
            )
        }

        composable(
            route = "${Screen.TaskDetailScreen.route}/{id}", arguments = listOf(
                navArgument("id") {
                    type = NavType.LongType
                }
            )) { entry ->
            val taskId = entry.arguments?.getLong("id") ?: 0L
            TaskDetailedRoute(
                id = taskId,
                tasksViewModel = viewModel,
                onBack = navController::popBackStack,
                onEdit = { editTaskId ->
                    navController.navigate("${Screen.AddTaskScreen.route}/$editTaskId")
                }
            )
        }

        composable(
            route = "${Screen.AddTaskScreen.route}/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.LongType
                }
            )) { entry ->
            val id = entry.arguments?.getLong("id")

            AddTaskRoute(
                id = id,
                viewModel = viewModel,
                onBack = navController::popBackStack
            )
        }
    }
}