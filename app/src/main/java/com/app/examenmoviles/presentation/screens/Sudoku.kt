package com.app.examenmoviles.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.examenmoviles.presentation.screens.components.NumberPad
import com.app.examenmoviles.presentation.screens.components.SudokuBoard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SudokuScreen(
    viewModel: SudokuViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Sudoku") },
            )
        },
    ){ padding ->
        when {
            uiState.isLoading -> {
                Box(
                    Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            uiState.error != null -> {
                Text(
                    text = uiState.error!!,
                    modifier = Modifier.padding(16.dp)
                )
            }

            else -> {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(padding),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    NumberPad(onValueChange = {
                        viewModel.onCellValueChanged(it)
                    })

                    Spacer(Modifier.height(16.dp))

                    SudokuBoard(
                        puzzle = uiState.puzzle,
                        selectedRow = uiState.selectedRow,
                        selectedCol = uiState.selectedCol,
                        onSelectCell = { r, c -> viewModel.onCellSelected(r, c) },
                        onValueChange = { value -> viewModel.onCellValueChanged(value) }
                    )
                }
            }
        }

    }
}
