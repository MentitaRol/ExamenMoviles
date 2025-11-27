package com.app.examenmoviles.presentation.screens

data class SudokuUiState (
    val puzzle: List<List<Int?>> = emptyList(),
    val solution: List<List<Int?>> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedRow: Int? = null,
    val selectedCol: Int? = null
)