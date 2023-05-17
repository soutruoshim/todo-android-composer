package com.srhdp.to_docompose.ui.screen.list

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
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
import com.srhdp.to_docompose.components.PriorityItem
import com.srhdp.to_docompose.ui.theme.LARGE_PADDING

@Composable
fun ListAppBar() {
    DefaultListAppBar(
        onSearchClick = {},
        onSortClick = {},
        onDeleteClicked = {},
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultListAppBar(
    onSearchClick: () -> Unit = {},
    onSortClick: (Priority) -> Unit,
    onDeleteClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = "Tasks")
        },
        actions = {
            ListAppBarActions(onSearchClick = onSearchClick, onSortClick = onSortClick, onDeleteClicked = onDeleteClicked)
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
    )
}

@Composable
fun ListAppBarActions(
    onSearchClick: () -> Unit,
    onSortClick: (Priority) -> Unit,
    onDeleteClicked: () -> Unit
) {
    SearchAction(
        onSearchClick = onSearchClick
    )
    SortAction(onSortClick = onSortClick)
    DeleteAllAction(onDeleteClicked = onDeleteClicked)
}

@Composable
fun SearchAction(
    onSearchClick: () -> Unit
) {
    IconButton(onClick = { }) {
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
    onDeleteClicked:()->Unit
){
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
            DropdownMenuItem(onClick = {
                expanded = false
                onDeleteClicked()
            }, text = { Text(modifier = Modifier.padding(start = LARGE_PADDING), text = stringResource(id = R.string.delete_all)) })
        }
    }
}

@Composable
@Preview
private fun DefaultListAppBarPreview() {
    DefaultListAppBar(
        onSearchClick = {},
        onSortClick = {},
        onDeleteClicked = {}
    )
}
