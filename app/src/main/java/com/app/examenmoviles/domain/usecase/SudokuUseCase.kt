package com.app.examenmoviles.domain.usecase

import com.app.examenmoviles.domain.common.Result
import com.app.examenmoviles.domain.model.Sudoku
import com.app.examenmoviles.domain.repository.SudokuRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SudokuUseCase @Inject constructor(
    private val repository: SudokuRepository

){
    operator fun invoke(): Flow<Result<Sudoku>> = flow{
        try {
            emit(Result.Loading)
            val sudoku = repository.getSudokuGame()
            emit(Result.Success(sudoku))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}