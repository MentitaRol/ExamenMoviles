package com.app.examenmoviles.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.examenmoviles.domain.common.Result
import com.app.examenmoviles.domain.usecase.SudokuUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SudokuViewModel @Inject constructor(
    private val sudokuUseCase: SudokuUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(SudokuUiState())
    val uiState: StateFlow<SudokuUiState> = _uiState

    init {
        loadSudokuGame()
    }

    private fun loadSudokuGame() {
        viewModelScope.launch {
            sudokuUseCase().collect { result ->
                _uiState.update { state ->
                    when (result) {
                        is Result.Loading -> {
                            state.copy(
                                isLoading = true,
                                error = null
                            )
                        }

                        is Result.Success -> {
                            state.copy(
                                isLoading = false,
                                puzzle = result.data.puzzle,
                                solution = result.data.solution,
                                error = null
                            )
                        }

                        is Result.Error -> {
                            state.copy(
                                isLoading = false,
                                error = result.exception.message ?: "Unknown error"
                            )
                        }
                    }
                }
            }
        }
    }

    fun onCellSelected(row: Int, col: Int) {
        val puzzle = _uiState.value.puzzle
        if (puzzle[row][col] != null) return
        _uiState.update { it.copy(selectedRow = row, selectedCol = col) }
    }

    fun onCellValueChanged(newValue: Int?) {
        val row = uiState.value.selectedRow ?: return
        val col = uiState.value.selectedCol ?: return

        val updatedPuzzle = uiState.value.puzzle.mapIndexed { r, rowList ->
            rowList.mapIndexed { c, value ->
                if (r == row && c == col) newValue else value
            }
        }

        _uiState.update { it.copy(puzzle = updatedPuzzle) }
    }
}