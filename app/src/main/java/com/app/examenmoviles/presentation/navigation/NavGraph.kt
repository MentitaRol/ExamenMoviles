package com.app.examenmoviles.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.examenmoviles.presentation.screens.SudokuScreen

const val API_KEY = "apiKey"

sealed class Screen(
    val route: String,
) {
    object Home : Screen("sudoku")
}

@Composable
fun SudokuNavGraph(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
){
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ){
        composable(Screen.Home.route){
            SudokuScreen()
        }
    }
}