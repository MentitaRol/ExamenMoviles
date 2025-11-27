package com.app.examenmoviles.presentation.screens.components

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
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .border(
                width = if (isSelected) 3.dp else 1.dp,
                color = (if (isSelected) Color.Blue else Color.LightGray) as Color
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = value?.toString() ?: "",
            style = MaterialTheme.typography.titleLarge
        )
    }
}
