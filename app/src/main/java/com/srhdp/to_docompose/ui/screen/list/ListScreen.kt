package com.srhdp.to_docompose.ui.screen.list

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Arrangement.Top
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.srhdp.to_docompose.R
import com.srhdp.to_docompose.ui.viewmodels.SharedViewModel
import com.srhdp.to_docompose.util.Action
import com.srhdp.to_docompose.util.SearchAppBarState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListScreen(
    navigateToTaskScreen:(Int) -> Unit,
    sharedViewModel: SharedViewModel
){
    LaunchedEffect(key1 = true, ){
        sharedViewModel.getAllTasks()
    }

    val action by sharedViewModel.action

    val allTasks by sharedViewModel.allTasks.collectAsState()
    val searchedTasks by sharedViewModel.searchedTasks.collectAsState()
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