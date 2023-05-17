package com.srhdp.to_docompose.ui.screen.list

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.srhdp.to_docompose.R

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListScreen(
    navigateToTaskScreen:(Int) -> Unit
){
   Scaffold(
       topBar = {
           ListAppBar()
       },
       content = {},
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

@Composable
@Preview
private fun ListScreenPreview(){
    ListScreen(navigateToTaskScreen = {})
}