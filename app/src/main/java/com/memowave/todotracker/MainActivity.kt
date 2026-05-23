package com.memowave.todotracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.memowave.todotracker.ui.navigation.NavGraph
import com.memowave.todotracker.ui.theme.ThemeType
import com.memowave.todotracker.ui.theme.ThemeViewModel
import com.memowave.todotracker.ui.theme.TodoTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            TodoTracker()
        }
    }
}

@Composable
fun TodoTracker() {
    val navController = rememberNavController()
    val themeViewModel: ThemeViewModel = viewModel()
    val themeType by themeViewModel.theme.collectAsStateWithLifecycle()

    val isDarkTheme = when (themeType) {
        ThemeType.LIGHT -> false
        ThemeType.DARK -> true
    }

    TodoTrackerTheme(darkTheme = isDarkTheme) {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Surface(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.background)
                    .fillMaxSize()
                    .padding(
                        innerPadding
                    ),
            ) {
                NavGraph(navController,
                    currentTheme = themeType,
                    onThemeChanged = { newTheme ->
                        themeViewModel.setTheme(newTheme)
                    })
            }
        }
    }
}