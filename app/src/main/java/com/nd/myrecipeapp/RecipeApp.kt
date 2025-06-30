package com.nd.myrecipeapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RecipeApp(navcontroller: NavHostController) {
 val recipeViewModel: MainViewModel = viewModel()
 val viewstate by recipeViewModel.categorieState

    NavHost(navController = navcontroller,
        startDestination = Screen.RecipeScreen.route) {
        composable(route = Screen.RecipeScreen.route) {
            RecipeScreen(viewstate = viewstate,
                navigatetodetail = {
                    navcontroller.currentBackStackEntry ?.savedStateHandle?.set("category", it)
                    navcontroller.navigate(Screen.DetailScreen.route)
                }
                )
        }
        composable(route = Screen.DetailScreen.route) {
            val category = navcontroller.previousBackStackEntry?.savedStateHandle?.
            get<Category>("category")?:Category("","","","")
            CategoryDetailScreen(category = category)
            }
        }
    }

