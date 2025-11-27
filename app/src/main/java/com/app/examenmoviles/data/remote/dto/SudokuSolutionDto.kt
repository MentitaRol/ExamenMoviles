package com.app.examenmoviles.data.remote.dto

import com.google.gson.annotations.SerializedName

data class SudokuSolutionDto (
    @SerializedName("solution") val solution: List<List<Int?>>
)