package com.app.examenmoviles.data.local.model

data class SudokuCache (
    val puzzle: List<List<Int?>>,
    val initialPuzzle: List<List<Int?>>,
    val solution: List<List<Int?>>,
    val lastUpdate: Long
)