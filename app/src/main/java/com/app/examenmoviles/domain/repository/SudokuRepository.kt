package com.app.examenmoviles.domain.repository

import com.app.examenmoviles.data.local.model.SudokuCache
import com.app.examenmoviles.domain.model.Sudoku

interface SudokuRepository {
    suspend fun getSudokuGame(): Sudoku
    suspend fun getSavedSudoku(): SudokuCache?
    suspend fun saveSudoku(cache: SudokuCache)
}