package com.app.examenmoviles.data.local.preferences

import android.content.Context
import android.content.SharedPreferences
import com.app.examenmoviles.data.local.model.SudokuCache
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SudokuPreferences
    @Inject
    constructor(
        @ApplicationContext
        private val context: Context,
                private val gson: Gson
    ) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences(
            PreferencesConstants.PREF_NAME,
            Context.MODE_PRIVATE,
        )

    fun saveSudoku(cache: SudokuCache) {
        val json = gson.toJson(cache)
        prefs.edit()
            .putString(PreferencesConstants.KEY_SUDOKU_CACHE, json)
            .apply()
    }

    fun loadSudoku(): SudokuCache? {
        val json = prefs.getString(PreferencesConstants.KEY_SUDOKU_CACHE, null)
            ?: return null

        return gson.fromJson(json, SudokuCache::class.java)
    }

    fun clear() {
        prefs.edit()
            .remove(PreferencesConstants.KEY_SUDOKU_CACHE)
            .apply()
    }
    }