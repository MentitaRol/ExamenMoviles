package com.app.examenmoviles.presentation.screens.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NumberPad(onValueChange: (Int?) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        (1..9).forEach { num ->
            Button(
                onClick = { onValueChange(num) },
                modifier = Modifier.size(width = 80.dp, height = 56.dp)
            ) {
                Text(
                    text = num.toString(),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }


        Button(onClick = { onValueChange(null) }) {
            Text("Clear")
        }
    }
}
