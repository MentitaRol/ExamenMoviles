package com.app.examenmoviles.data.mapper

import com.app.examenmoviles.data.remote.dto.SudokuDto
import com.app.examenmoviles.domain.model.Sudoku

fun SudokuDto.toDomain(): Sudoku {
    return Sudoku(
        puzzle = puzzle,
        solution = solution
    )
}