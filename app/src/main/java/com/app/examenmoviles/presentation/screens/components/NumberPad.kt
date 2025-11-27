package com.app.examenmoviles.presentation.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NumberPad(onValueChange: (Int?) -> Unit) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        (1..4).forEach { num ->
            Button(onClick = { onValueChange(num) }) {
                Text(num.toString())
            }
        }

        Button(onClick = { onValueChange(null) }) {
            Text("Clear")
        }
    }
}
