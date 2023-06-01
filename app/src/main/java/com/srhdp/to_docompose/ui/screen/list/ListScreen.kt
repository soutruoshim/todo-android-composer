package com.srhdp.to_docompose.ui.screen.list

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.srhdp.to_docompose.R
import com.srhdp.to_docompose.ui.viewmodels.SharedViewModel
import com.srhdp.to_docompose.util.SearchAppBarState

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListScreen(
    navigateToTaskScreen:(Int) -> Unit,
    sharedViewModel: SharedViewModel
){
    LaunchedEffect(key1 = true, ){
        sharedViewModel.getAllTasks()
        sharedViewModel.readSortState()
    }

    val action by sharedViewModel.action

    val allTasks by sharedViewModel.allTasks.collectAsState()
    val searchedTasks by sharedViewModel.searchedTasks.collectAsState()
    val sortState by sharedViewModel.sortState.collectAsState()
    val lowPriorityTask by sharedViewModel.lowPriorityTasks.collectAsState()
    val highPriorityTask by sharedViewModel.highPriorityTasks.collectAsState()

    val searchAppBarState:SearchAppBarState by sharedViewModel.searchAppBarState
    val searchTextState:String by sharedViewModel.searchTextState

    sharedViewModel.handleDatabaseAction(action = action)
   Scaffold(
       topBar = {
          ListAppBar(
              sharedViewModel = sharedViewModel,
              searchAppBarState = searchAppBarState,
              searchTextState = searchTextState
          )
       },
       content = {
           Box(
               modifier = Modifier.padding(it)
           ) {

               ListContent(
                   allTasks = allTasks,
                   searchedTasks = searchedTasks,
                   lowPriorityTasks = lowPriorityTask,
                   highPriorityTasks = highPriorityTask,
                   sortState = sortState,
                   searchAppBarState = searchAppBarState,
                   navigateToTaskScreen = navigateToTaskScreen
               )
           }

       },
       floatingActionButton = {
        ListFab(onFabClicked = navigateToTaskScreen)
       }
   )

}

@Composable
fun ListFab(onFabClicked: (Int) -> Unit){
    FloatingActionButton(onClick = {
        onFabClicked(-1)
    }) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = stringResource(id = R.string.add_button) )
    }
}


//@Composable
//fun DisplaySnackBar(
//    scaffoldState: ScaffoldState,
//    handleDatabaseActions:()->Unit,
//    taskTitle:String,
//    action:Action
//){
//    handleDatabaseActions()
//    val scope = rememberCoroutineScope()
//    LaunchedEffect(key1 = action){
//        if(action != Action.NO_ACTION){
//            scope.launch {
//                val snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
//                    message = "${action.name}: $taskTitle",
//                    actionLabel = "OK"
//                )
//            }
//        }
//    }
//}