package com.app.examenmoviles.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.examenmoviles.data.local.model.SudokuCache
import com.app.examenmoviles.domain.common.Result
import com.app.examenmoviles.domain.usecase.SudokuUseCase
import com.app.examenmoviles.domain.usecase.CheckSolutionUseCase
import com.app.examenmoviles.domain.usecase.GetSavedSudokuUseCase
import com.app.examenmoviles.domain.usecase.SaveCurrentGameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SudokuViewModel @Inject constructor(
    private val sudokuUseCase: SudokuUseCase,
    private val checkSolutionUseCase: CheckSolutionUseCase,
    private val getSavedSudokuUseCase: GetSavedSudokuUseCase,
    private val saveCurrentGameUseCase: SaveCurrentGameUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(SudokuUiState())
    val uiState: StateFlow<SudokuUiState> = _uiState

    init {
        loadSavedGameOrNew()
    }
    private fun loadSavedGameOrNew() {
        viewModelScope.launch {
            val saved = getSavedSudokuUseCase()
            if (saved != null) {
                _uiState.value = SudokuUiState(
                    puzzle = saved.puzzle.map { it.toMutableList() },
                    initialPuzzle = saved.initialPuzzle,
                    solution = saved.solution,
                    isLoading = false,
                    message = null
                )
            } else {
                loadSudokuGame()
            }
        }
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
                                puzzle = result.data.puzzle.map { it.toMutableList() },
                                initialPuzzle = result.data.puzzle,
                                solution = result.data.solution,
                                error = null
                            )
                        }

                        is Result.Error -> {
                            state.copy(
                                isLoading = false,
                                error = result.exception.message ?: "Error desconocido"
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
        val state = _uiState.value

        val row = uiState.value.selectedRow ?: return
        val col = uiState.value.selectedCol ?: return

        if (state.initialPuzzle[row][col] != null) return

        val updatedPuzzle = uiState.value.puzzle.mapIndexed { r, rowList ->
            rowList.mapIndexed { c, value ->
                if (r == row && c == col) newValue else value
            }
        }

        _uiState.update { it.copy(puzzle = updatedPuzzle) }

        saveCurrentGame()
    }

    fun verifySolution() {
        val state = _uiState.value

        val isCorrect = checkSolutionUseCase(
            current = state.puzzle,
            solution = state.solution
        )

        _uiState.update {
            it.copy(
                message = if (isCorrect)
                    "¡La solución es correcta!"
                else
                    "La solución es incorrecta. Sigue intentando."
            )
        }
    }

    fun resetSudoku(){
        val state = _uiState.value
        val resetPuzzle = state.initialPuzzle.map { row ->
            row.map { it }.toMutableList()
        }
        _uiState.update {
            it.copy(
                puzzle = resetPuzzle,
                selectedRow = null,
                selectedCol = null,
                message = null
            )
        }

        saveCurrentGame()

    }

    fun saveCurrentGame() {
        val state = _uiState.value
        val cache = SudokuCache(
            puzzle = state.puzzle,
            initialPuzzle = state.initialPuzzle,
            solution = state.solution,
            lastUpdate = System.currentTimeMillis()
        )
        viewModelScope.launch {
            saveCurrentGameUseCase(cache)
        }
    }

    fun loadSavedGame() {
        viewModelScope.launch {
            val saved = getSavedSudokuUseCase()
            if (saved != null) {
                _uiState.value = SudokuUiState(
                    puzzle = saved.puzzle.map { it.toMutableList() },
                    initialPuzzle = saved.initialPuzzle,
                    solution = saved.solution,
                    isLoading = false,
                    selectedRow = null,
                    selectedCol = null,
                    message = "Partida cargada"
                )
            } else {
                _uiState.update {
                    it.copy(message = "No hay partida guardada")
                }
            }
        }
    }

    fun loadNewGame(){
        loadSudokuGame()
    }

}