package com.srhdp.to_docompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.srhdp.to_docompose.navigation.destinations.listComposable
import com.srhdp.to_docompose.navigation.destinations.splashComposable
import com.srhdp.to_docompose.navigation.destinations.taskComposable
import com.srhdp.to_docompose.ui.viewmodels.SharedViewModel
import com.srhdp.to_docompose.util.Constants.LIST_SCREEN
import com.srhdp.to_docompose.util.Constants.SPLASH_SCREEN

@Composable
fun SetupNavigation(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    val screen = remember(navController) {
        Screens(navController = navController)
    }

    NavHost(navController = navController, startDestination = SPLASH_SCREEN) {
        splashComposable(
            navigateToListScreen = screen.splash
        )
        listComposable(
            navigateToTaskScreen = screen.list,
            sharedViewModel = sharedViewModel
        )
        taskComposable(
            sharedViewModel = sharedViewModel,
            navigateToListScreen = screen.task
        )
    }
}