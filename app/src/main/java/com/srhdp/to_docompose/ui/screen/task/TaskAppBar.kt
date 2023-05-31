package com.srhdp.to_docompose.ui.screen.task


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.srhdp.to_docompose.data.models.TodoTask
import com.srhdp.to_docompose.util.Action
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import com.srhdp.to_docompose.R
import com.srhdp.to_docompose.components.DisplayAlertDialog


@Composable
fun TaskAppBar(selectedTask: TodoTask?, navigateToListScreens: (Action) -> Unit){
     if(selectedTask == null){
         NewTaskAppBar(navigateToListScreens = navigateToListScreens)
     }else{
         ExistingTaskAppBar(selectedTask = selectedTask, navigateToListScreens = navigateToListScreens )
     }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTaskAppBar(navigateToListScreens: (Action) -> Unit) {
        TopAppBar(navigationIcon = {
            BackAction(onBackClicked = navigateToListScreens)
        },
        title = { Text(text = "Add Task") },
        actions = {
             AddAction(addClicked = navigateToListScreens)
           }
        )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExistingTaskAppBar(
    selectedTask:TodoTask,
    navigateToListScreens: (Action) -> Unit) {
    TopAppBar(
        navigationIcon = {
          CloseAction(onCloseClicked = navigateToListScreens)
    },
        title = { Text(text = selectedTask.title, maxLines = 1, overflow = TextOverflow.Ellipsis) },
        actions = {
            ExistingTaskAppBarAction(selectedTask = selectedTask, navigateToListScreens = navigateToListScreens)
        }
    )
}

@Composable
fun ExistingTaskAppBarAction(
    selectedTask:TodoTask,
    navigateToListScreens: (Action) -> Unit
){
    var openDialog by remember {
          mutableStateOf(false)
    }

    DisplayAlertDialog(
        title = stringResource(id = R.string.delete_task, selectedTask.title),
        message = stringResource(id = R.string.delete_task_confirm, selectedTask.title),
        openDialog = openDialog,
        closeDialog = {
            openDialog = false
        },
        onYesClicked = {
            navigateToListScreens(Action.DELETE)
        }
    )

    DeleteAction(onDeleteClicked = {
        openDialog = true
    })
    UpdateAction(onUpdateClicked = navigateToListScreens)
}

@Composable
fun BackAction(onBackClicked: (Action) -> Unit) {
    IconButton(onClick = { onBackClicked(Action.NO_ACTION) }) {
        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back Arrow")
    }
}

@Composable
fun AddAction(addClicked: (Action) -> Unit) {
    IconButton(onClick = { addClicked(Action.ADD) }) {
        Icon(imageVector = Icons.Filled.Check, contentDescription = "Add Check")
    }
}

@Composable
fun CloseAction(onCloseClicked: (Action) -> Unit) {
    IconButton(onClick = { onCloseClicked(Action.NO_ACTION) }) {
        Icon(imageVector = Icons.Filled.Close, contentDescription = "Close")
    }
}

@Composable
fun DeleteAction(onDeleteClicked: () -> Unit) {
    IconButton(onClick = { onDeleteClicked() }) {
        Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete")
    }
}
@Composable
fun UpdateAction(onUpdateClicked: (Action) -> Unit) {
    IconButton(onClick = { onUpdateClicked(Action.UPDATE) }) {
        Icon(imageVector = Icons.Filled.Check, contentDescription = "Update")
    }
}


