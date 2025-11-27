package com.app.examenmoviles.presentation.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SudokuCell(
    value: Int?,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isInitial: Boolean,
) {

    val bgColor = if (isInitial) Color(0xFFE0E0E0) else Color.White
    val textColor = if (isInitial) Color.DarkGray else Color.Black

    Box(
        modifier = modifier
            .border(
                width = if (isSelected) 3.dp else 1.dp,
                color = (if (isSelected) Color.Blue else Color.LightGray) as Color
            )
            .background(bgColor)
            .let { if (!isInitial) it.clickable { onClick() } else it },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = value?.toString() ?: "",
            style = MaterialTheme.typography.titleLarge,
            color = textColor
        )
    }
}
