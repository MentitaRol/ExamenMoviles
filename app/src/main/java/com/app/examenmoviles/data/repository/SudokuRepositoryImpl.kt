package com.app.examenmoviles.data.repository

import com.app.examenmoviles.data.local.model.SudokuCache
import com.app.examenmoviles.data.local.preferences.PreferencesConstants
import com.app.examenmoviles.data.local.preferences.SudokuPreferences
import com.app.examenmoviles.data.mapper.toDomain
import com.app.examenmoviles.data.remote.api.SudokuApi
import com.app.examenmoviles.domain.model.Sudoku
import com.app.examenmoviles.domain.repository.SudokuRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SudokuRepositoryImpl @Inject constructor(
    private val api: SudokuApi,
    private val preferences: SudokuPreferences,
) : SudokuRepository {

    companion object {
        private const val API_KEY = "JsurHpFBO70jRaeN9q3MJA==RUHmoiaNSEwvqWDN"
    }

    override suspend fun getSudokuGame(): Sudoku {
        val response = api.getSudokuGame(API_KEY).toDomain()
        return response
    }

    override suspend fun getSavedSudoku(): SudokuCache? {
        return preferences.loadSudoku()
    }

    override suspend fun saveSudoku(cache: SudokuCache) {
        preferences.saveSudoku(cache)
    }
}