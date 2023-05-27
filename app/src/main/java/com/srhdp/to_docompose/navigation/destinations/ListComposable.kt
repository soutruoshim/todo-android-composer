package com.srhdp.to_docompose.navigation.destinations

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.srhdp.to_docompose.ui.screen.list.ListScreen
import com.srhdp.to_docompose.ui.viewmodels.SharedViewModel
import com.srhdp.to_docompose.util.Constants.LIST_ARGUMENT_KEY
import com.srhdp.to_docompose.util.Constants.LIST_SCREEN
import com.srhdp.to_docompose.util.toAction

fun NavGraphBuilder.listComposable(
    navigateToTaskScreen:(Int) -> Unit,
    sharedViewModel: SharedViewModel
){
    composable(
        route = LIST_SCREEN,
        arguments = listOf(navArgument(LIST_ARGUMENT_KEY){
            type = NavType.StringType
        })
    ){navBackStackEntry->
          val action = navBackStackEntry.arguments?.getString(LIST_ARGUMENT_KEY).toAction()
          LaunchedEffect(key1 = action){
              sharedViewModel.action.value = action
          }
          ListScreen(navigateToTaskScreen = navigateToTaskScreen, sharedViewModel = sharedViewModel)
    }
}