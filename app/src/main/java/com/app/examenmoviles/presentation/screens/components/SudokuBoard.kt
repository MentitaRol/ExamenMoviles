package com.app.examenmoviles.presentation.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SudokuBoard(
    puzzle: List<List<Int?>>,
    selectedRow: Int?,
    selectedCol: Int?,
    onSelectCell: (Int, Int) -> Unit,
    onValueChange: (Int?) -> Unit
) {
    Column(
        Modifier
            .padding(16.dp)
            .aspectRatio(1f)
    ) {
        puzzle.forEachIndexed { rowIndex, row ->
            Row(Modifier.weight(1f)) {
                row.forEachIndexed { colIndex, value ->
                    SudokuCell(
                        value = value,
                        isSelected = rowIndex == selectedRow && colIndex == selectedCol,
                        onClick = { onSelectCell(rowIndex, colIndex) },
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                    )
                }
            }
        }
    }

    Spacer(Modifier.height(16.dp))

    NumberPad(onValueChange)
}
