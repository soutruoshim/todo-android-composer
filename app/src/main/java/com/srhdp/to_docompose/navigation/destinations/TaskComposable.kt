package com.srhdp.to_docompose.navigation.destinations

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.srhdp.to_docompose.ui.screen.task.TaskScreen
import com.srhdp.to_docompose.ui.viewmodels.SharedViewModel
import com.srhdp.to_docompose.util.Action
import com.srhdp.to_docompose.util.Constants
import com.srhdp.to_docompose.util.Constants.TASK_ARGUMENT_KEY

fun NavGraphBuilder.taskComposable(
    sharedViewModel: SharedViewModel,
    navigateToListScreen:(Action) -> Unit
){
    composable(
        route = Constants.TASK_SCREEN,
        arguments = listOf(navArgument(Constants.TASK_ARGUMENT_KEY){
            type = NavType.IntType
        })
    ){navBackStackEntry ->  
        val taskId = navBackStackEntry.arguments!!.getInt(TASK_ARGUMENT_KEY)
        sharedViewModel.getSelectedTask(taskId)
        val selectedTask by sharedViewModel.selectedTask.collectAsState()
        
        LaunchedEffect(key1 = selectedTask){
            sharedViewModel.updateTaskFields(selectedTask = selectedTask)
        }
        
        TaskScreen(selectedTask = selectedTask, sharedViewModel= sharedViewModel, navigateToListScreens = navigateToListScreen)
    }
}