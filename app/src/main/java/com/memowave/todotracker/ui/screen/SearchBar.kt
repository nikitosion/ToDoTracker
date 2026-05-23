package com.memowave.todotracker.ui.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    value: String,
    onValueChanged: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        placeholder = { Text(text = "Введите что-нибудь...") },
        label = { Text(text = "Поиск") },
        shape = RoundedCornerShape(24.dp),
        onValueChange = onValueChanged,
    )
}


@Composable
@Preview
fun SearchBarPreview() {
    SearchBar(
        modifier = Modifier,
        value = "",
        onValueChanged = {}
    )
}