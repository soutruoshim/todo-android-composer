package com.srhdp.to_docompose.ui.screen.task

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.srhdp.to_docompose.components.PriorityDropDown
import com.srhdp.to_docompose.data.models.Priority
import com.srhdp.to_docompose.ui.theme.LARGE_PADDING
import com.srhdp.to_docompose.ui.theme.MEDIUM_PADDING

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskContent(
    title:String,
    onTitleChange:(String)->Unit,
    description:String,
    onDescriptionChange:(String)->Unit,
    priority: Priority,
    onPrioritySelected:(Priority)->Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = LARGE_PADDING)
    )
    {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = title,
            onValueChange = {onTitleChange(it)},
            label = { Text(text = "Title")},
            textStyle = MaterialTheme.typography.bodyMedium,
            singleLine = true
        )
        Divider(

            modifier = Modifier.height(MEDIUM_PADDING),
            color = MaterialTheme.colorScheme.background
        )

        PriorityDropDown(priority = priority, onPrioritySelected = onPrioritySelected)
        OutlinedTextField(
            modifier = Modifier.fillMaxSize(),
            value = description,
            onValueChange = {onDescriptionChange(it)},
            label = { Text(text = "Description")},
            textStyle = MaterialTheme.typography.bodyMedium,
            singleLine = true
        )
    }
}

@Composable
@Preview
fun PriorityDropDownPreview() {
    TaskContent(
        title = "Title",
        onTitleChange = {},
        description = "",
        onDescriptionChange = {},
        priority = Priority.LOW,
        onPrioritySelected = {}
    )
}