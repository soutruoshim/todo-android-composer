package com.srhdp.to_docompose.ui.screen.task

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.srhdp.to_docompose.data.models.Priority
import com.srhdp.to_docompose.data.models.TodoTask
import com.srhdp.to_docompose.ui.viewmodels.SharedViewModel
import com.srhdp.to_docompose.util.Action

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(selectedTask: TodoTask?, sharedViewModel: SharedViewModel, navigateToListScreens: (Action) -> Unit){
    val title:String by sharedViewModel.title
    val priority:Priority by sharedViewModel.priority
    val description:String by sharedViewModel.description

    val context = LocalContext.current

    Scaffold (
        topBar = {
              TaskAppBar(selectedTask = selectedTask,  navigateToListScreens = {action->
                  if(action == Action.NO_ACTION){
                      navigateToListScreens(action)
                  }else{
                      if (sharedViewModel.validateFields()){
                          navigateToListScreens(action)
                      }else{
                          displayToast(context = context)
                      }
                  }
              })
        },
        content = {
            Box(
                modifier = Modifier.padding(it)
            ) {
                TaskContent(
                    title = title,
                    onTitleChange = {sharedViewModel.updateTitle(it)},
                    description = description,
                    onDescriptionChange = {
                                    sharedViewModel.description.value = it
                    },
                    priority = priority,
                    onPrioritySelected = {
                        sharedViewModel.priority.value = it
                    }
                )
            }
        }
    )
}
fun displayToast(context:Context){
    Toast.makeText(context, "Field Empty", Toast.LENGTH_SHORT).show()
}