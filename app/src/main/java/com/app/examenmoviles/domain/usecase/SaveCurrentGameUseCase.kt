package com.app.examenmoviles.domain.usecase

import com.app.examenmoviles.data.local.model.SudokuCache
import com.app.examenmoviles.domain.repository.SudokuRepository
import javax.inject.Inject

class SaveCurrentGameUseCase @Inject constructor(
    private val repository: SudokuRepository
) {
    suspend operator fun invoke(cache: SudokuCache) {
        repository.saveSudoku(cache)
    }
}