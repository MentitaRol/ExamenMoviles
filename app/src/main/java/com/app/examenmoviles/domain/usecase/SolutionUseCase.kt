package com.app.examenmoviles.domain.usecase

import javax.inject.Inject

class CheckSolutionUseCase @Inject constructor() {

    operator fun invoke(
        current: List<List<Int?>>,
        solution: List<List<Int?>>
    ): Boolean {
        for (r in 0 until 9) {
            for (c in 0 until 9) {
                val value = current[r][c] ?: return false
                if (value != solution[r][c]) return false
            }
        }
        return true
    }
}
