package com.memowave.todotracker.ui.screen.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.memowave.todotracker.R
import com.memowave.todotracker.ui.theme.ThemeType


@Composable
fun ThemeCheck(modifier: Modifier = Modifier, themeType: ThemeType, isChecked: Boolean = false, onClick: () -> Unit = {}) {

    val containerColor = if (isChecked) {
        MaterialTheme.colorScheme.primaryContainer
    } else {
        MaterialTheme.colorScheme.surfaceVariant
    }

    val contentColor = if (isChecked) {
        MaterialTheme.colorScheme.onPrimaryContainer
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant
    }

    Box(
        modifier = modifier.background(
            color = containerColor,
            shape = RoundedCornerShape(30.dp)
        ),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .width(150.dp)
                .height(150.dp)
                .clickable(onClick = onClick),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier.size(48.dp),
                painter = painterResource(
                    id = when (themeType) {
                        ThemeType.LIGHT -> {
                            R.drawable.round_light_mode_24
                        }

                        ThemeType.DARK -> {
                            R.drawable.round_dark_mode_24
                        }
                    }
                ),
                contentDescription = null,
                tint = contentColor
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = when (themeType) {
                    ThemeType.LIGHT -> "Light Theme"
                    ThemeType.DARK -> "Dark Theme"
                },
                style = MaterialTheme.typography.bodyLarge,
                color = contentColor
            )
        }
    }
}

@Composable
@Preview
fun ThemeCheckPreview() {
    ThemeCheck(themeType = ThemeType.DARK, isChecked = false)
}

@Composable
@Preview
fun ThemeCheckedPreview() {
    ThemeCheck(themeType = ThemeType.DARK, isChecked = true)
}

@Composable
@Preview
fun LightThemeCheckPreview() {
    ThemeCheck(themeType = ThemeType.LIGHT, isChecked = false)
}

@Composable
@Preview
fun LightThemeCheckedPreview() {
    ThemeCheck(themeType = ThemeType.LIGHT, isChecked = true)
}