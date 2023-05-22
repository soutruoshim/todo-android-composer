package com.srhdp.to_docompose.ui.screen.list

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    }

    val allTasks by sharedViewModel.allTasks.collectAsState()

    val searchAppBarState:SearchAppBarState by sharedViewModel.searchAppBarState
    val searchTextState:String by sharedViewModel.searchTextState
//       Scaffold(
//           topBar = {
//              ListAppBar(
//                  sharedViewModel = sharedViewModel,
//                  searchAppBarState = searchAppBarState,
//                  searchTextState = searchTextState
//              )
//           },
//           content = {
//                     ListContent(
//                         tasks = allTasks,
//                         navigateToTaskScreen = navigateToTaskScreen
//                     )
//           },
//           floatingActionButton = {
//            ListFab(onFabClicked = navigateToTaskScreen)
//           }
//       )
        Scaffold(
            topBar = {
                ListAppBar(
                    sharedViewModel = sharedViewModel,
                    searchAppBarState = searchAppBarState,
                    searchTextState = searchTextState
                )
            },
            floatingActionButton = {
                ListFab(onFabClicked = navigateToTaskScreen)
            }
        ) {contentPadding ->
            ListContent(
                tasks = allTasks,
                navigateToTaskScreen = navigateToTaskScreen
            )
        }
}

@Composable
fun ListFab(onFabClicked: (Int) -> Unit){
    FloatingActionButton(onClick = {
        onFabClicked(-1)
    }) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = stringResource(id = R.string.add_button) )
    }
}
