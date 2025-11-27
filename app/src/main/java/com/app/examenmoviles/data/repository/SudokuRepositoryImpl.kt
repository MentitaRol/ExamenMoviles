package com.app.examenmoviles.data.repository

import com.app.examenmoviles.data.mapper.toDomain
import com.app.examenmoviles.data.remote.api.SudokuApi
import com.app.examenmoviles.domain.model.Sudoku
import com.app.examenmoviles.domain.repository.SudokuRepository
import com.app.examenmoviles.presentation.navigation.API_KEY
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SudokuRepositoryImpl @Inject constructor(
    private val api: SudokuApi
) : SudokuRepository {

    companion object {
        private const val API_KEY = "JsurHpFBO70jRaeN9q3MJA==RUHmoiaNSEwvqWDN"
    }

    override suspend fun getSudokuGame(): Sudoku {
        return api.getSudokuGame(API_KEY).toDomain()
    }
}