package com.app.examenmoviles.data.remote.api

import com.app.examenmoviles.data.remote.dto.SudokuDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface SudokuApi{
    @GET("sudokugenerate")
    suspend fun getSudokuGame(
        @Query("width") width: Int = 4,
        @Query("height") height: Int = 4,
        @Query("difficulty") difficulty: String = "easy",
    ): SudokuDto
}