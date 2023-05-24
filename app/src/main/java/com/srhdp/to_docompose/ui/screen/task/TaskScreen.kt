package com.srhdp.to_docompose.ui.screen.task

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.srhdp.to_docompose.data.models.TodoTask
import com.srhdp.to_docompose.ui.viewmodels.SharedViewModel
import com.srhdp.to_docompose.util.Action

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(selectedTask: TodoTask?, navigateToListScreens: (Action) -> Unit){
    Scaffold (
        topBar = {
              TaskAppBar(selectedTask = selectedTask,  navigateToListScreens = navigateToListScreens)
        },
        content = {
            Box(
                modifier = Modifier.padding(it)
            ) {

            }
        }
    )
}