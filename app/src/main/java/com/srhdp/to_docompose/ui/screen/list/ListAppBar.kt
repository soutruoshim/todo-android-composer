package com.srhdp.to_docompose.ui.screen.list

import android.provider.CalendarContract
import androidx.compose.foundation.gestures.TransformableState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.srhdp.to_docompose.R
import com.srhdp.to_docompose.data.models.Priority
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.srhdp.to_docompose.components.DisplayAlertDialog
import com.srhdp.to_docompose.components.PriorityItem
import com.srhdp.to_docompose.ui.theme.LARGE_PADDING
import com.srhdp.to_docompose.ui.theme.TOP_APP_BAR_HEIGHT
import com.srhdp.to_docompose.ui.viewmodels.SharedViewModel
import com.srhdp.to_docompose.util.Action
import com.srhdp.to_docompose.util.SearchAppBarState
import com.srhdp.to_docompose.util.TrailingIconState

@Composable
fun ListAppBar(
    sharedViewModel: SharedViewModel,
    searchAppBarState: SearchAppBarState,
    searchTextState: String
) {
    when(searchAppBarState){
        SearchAppBarState.CLOSED -> {
            DefaultListAppBar(
                onSearchClick = {
                      sharedViewModel.searchAppBarState.value = SearchAppBarState.OPENED

                },
                onSortClick = {sharedViewModel.persisSortState(it)},
                onDeleteAllClicked = {
                    sharedViewModel.action.value = Action.DELETE_ALL
                },
            )
        }

        else -> {
            SearchAppBar(
                text= searchTextState,
                onTextChange = {newText ->
                               sharedViewModel.searchTextState.value = newText
                },
                onCloseClicked={
                    sharedViewModel.searchAppBarState.value = SearchAppBarState.CLOSED
                    sharedViewModel.searchTextState.value = ""
                },
                onSearchClicked={
                    sharedViewModel.searchDatabase(it)
                }
            )
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultListAppBar(
    onSearchClick: () -> Unit,
    onSortClick: (Priority) -> Unit,
    onDeleteAllClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.title))
        },
        actions = {
            ListAppBarActions(
                onSearchClick = onSearchClick,
                onSortClick = onSortClick,
                onDeleteAllClicked = onDeleteAllClicked
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
    )
}

@Composable
fun ListAppBarActions(
    onSearchClick: () -> Unit,
    onSortClick: (Priority) -> Unit,
    onDeleteAllClicked: () -> Unit
) {
    var openDialog by remember {
        mutableStateOf(false)
    }

    DisplayAlertDialog(
        title = stringResource(id = R.string.delete_task_all),
        message = stringResource(id = R.string.delete_task_all_confirm),
        openDialog = openDialog,
        closeDialog = {
            openDialog = false
        },
        onYesClicked = {
            onDeleteAllClicked()
            openDialog = false
        }
    )

    SearchAction(
        onSearchClick = onSearchClick
    )
    SortAction(onSortClick = onSortClick)
    DeleteAllAction(onDeleteClicked = {openDialog = true})
}

@Composable
fun SearchAction(
    onSearchClick: () -> Unit
) {
    IconButton(onClick = onSearchClick) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = stringResource(id = R.string.search_tasks)
        )

    }
}

@Composable
fun SortAction(
    onSortClick: (Priority) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    IconButton(onClick = { expanded = true }) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_filter_list),
            contentDescription = stringResource(
                id = R.string.sort_tasks
            )
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }) {
            DropdownMenuItem(onClick = {
                expanded = false
                onSortClick(Priority.LOW)
            }, text = { PriorityItem(Priority.LOW) })
            DropdownMenuItem(onClick = {
                expanded = false
                onSortClick(Priority.MEDIUM)
            }, text = { PriorityItem(Priority.MEDIUM) })
            DropdownMenuItem(onClick = {
                expanded = false
                onSortClick(Priority.HIGH)
            }, text = { PriorityItem(Priority.HIGH) })
            DropdownMenuItem(onClick = {
                expanded = false
                onSortClick(Priority.NONE)
            }, text = { PriorityItem(Priority.NONE) })
        }
    }
}

@Composable
fun DeleteAllAction(
    onDeleteClicked: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    IconButton(onClick = { expanded = true }) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_more_vert),
            contentDescription = stringResource(
                id = R.string.delete_all
            )
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }) {
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onDeleteClicked()
                },
                text = {
                    Text(
                        modifier = Modifier.padding(start = LARGE_PADDING),
                        text = stringResource(id = R.string.delete_all)
                    )
                })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit
) {
    var trailingIconState by remember {
        mutableStateOf(TrailingIconState.READY_TO_DELETE)
    }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(TOP_APP_BAR_HEIGHT),
        shadowElevation = 4.dp
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = text,
            onValueChange = {
                onTextChange(it)
            },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search),
                )
            },
            textStyle = TextStyle(
                //color = MaterialTheme.colorScheme.background,
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    onClick = { /*TODO*/ }
                ) {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "Search icon")
                }
            },
            trailingIcon = {
                IconButton(
                   onClick = {
                       when(trailingIconState){
                           TrailingIconState.READY_TO_DELETE ->{
                               onTextChange("")
                               trailingIconState = TrailingIconState.READY_TO_CLOSE
                           } TrailingIconState.READY_TO_CLOSE -> {
                                if(text.isNotEmpty()){
                                    onTextChange("")
                                }else{
                                    onCloseClicked()
                                    trailingIconState = TrailingIconState.READY_TO_DELETE
                                }
                           }
                       }
                   }
                ) {
                    Icon(imageVector = Icons.Filled.Close, contentDescription = "Close icon")
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(text)
                }
            ),

        )
    }
}

//@Composable
//@Preview
//private fun DefaultListAppBarPreview() {
//    DefaultListAppBar(
//        onSearchClick = {},
//        onSortClick = {},
//        onDeleteClicked = {}
//    )
//}
//
//@Composable
//@Preview
//private fun SearchAppBarPreview() {
//    SearchAppBar(
//        text= "Search",
//        onTextChange = {},
//        onCloseClicked={},
//        onSearchClicked={}
//    )
//}
