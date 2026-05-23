package com.memowave.todotracker.ui.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.memowave.todotracker.R

@Composable
fun TaskItem(
    modifier: Modifier = Modifier,
    id: Long = 0L,
    title: String = "Without name task",
    isTaskDone: Boolean = false,
    onTaskToggle: (Long, Boolean) -> Unit,
    onClick: (Long) -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color(0xFFE0E0E0),
                shape = RoundedCornerShape(30.dp)
            )
            .background(color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(30.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClick(id) },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.padding(vertical = 16.dp).weight(1f),
            text = title,
            style = MaterialTheme.typography.titleMedium
        )

        if (isTaskDone) {
            IconButton(
                onClick = {
                    onTaskToggle(id, !isTaskDone)
                }
            ) {
                Icon(
                    modifier = Modifier
                        .background(
                            color = Color(0xFFE8F5E9),
                            shape = RoundedCornerShape(50)
                        )
                        .padding(10.dp),
                    painter = painterResource(id = R.drawable.round_done_24),
                    contentDescription = "Done icon",
                    tint = Color(0xFF4CAF50)
                )

            }
        } else {
            OutlinedIconButton(
                onClick = { onTaskToggle(id, !isTaskDone) },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.round_done_24),
                    contentDescription = "Done button"
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, name = "Task")
fun TaskItemPreview() {
    TaskItem(
        modifier = Modifier.padding(16.dp),
        title = "Buy milk",
        isTaskDone = false,
        onTaskToggle = { _, _ -> }
    )
}

@Composable
@Preview(showBackground = true, name = "Task")
fun TaskItemDonePreview() {
    TaskItem(
        modifier = Modifier.padding(16.dp),
        title = "Buy milk",
        isTaskDone = true,
        onTaskToggle = { _, _ -> }
    )
}

@Composable
@Preview(showBackground = true, name = "Task — long text")
fun TaskItemLongTextPreview() {
    TaskItem(
        modifier = Modifier.padding(16.dp),
        title = "Write a long report about the quarterly results and send it to the team",
        isTaskDone = false,
        onTaskToggle = { _, _ -> }
    )
}