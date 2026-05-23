package com.memowave.todotracker.ui.screen.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.memowave.todotracker.ui.theme.ThemeType

@Composable
fun SettingsScreenRoute(
    currentTheme: ThemeType,
    onThemeChange: (ThemeType) -> Unit
) {
    SettingsScreen(
        currentTheme = currentTheme, onThemeChange = onThemeChange
    )
}

@Composable
fun SettingsScreen(
    currentTheme: ThemeType,
    onThemeChange: (ThemeType) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopStart
    ) {
        Column() {
            Text(
                text = "Выберите тему",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp)
            )
            Row(
                modifier = Modifier.padding(start = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ThemeCheck(themeType = ThemeType.LIGHT, isChecked = currentTheme == ThemeType.LIGHT, onClick = { onThemeChange(ThemeType.LIGHT) })
                ThemeCheck(themeType = ThemeType.DARK, isChecked = currentTheme == ThemeType.DARK, onClick = { onThemeChange(ThemeType.DARK) })
            }
        }
    }
}

@Composable
@Preview
fun SettingsScreenPreview() {
    SettingsScreen(
        currentTheme = ThemeType.LIGHT,
        onThemeChange = {}
    )
}