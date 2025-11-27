package com.app.examenmoviles.domain.repository

import com.app.examenmoviles.domain.model.Sudoku

interface SudokuRepository {
    suspend fun getSudokuGame(): Sudoku
}