package com.srhdp.to_docompose.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.srhdp.to_docompose.data.models.Priority
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.srhdp.to_docompose.ui.theme.PRIORITY_INDICATOR_SIZE

@Composable
fun PriorityDropDown(
    priority: Priority,
    onPrioritySelected:(Priority) -> Unit
){
    var expanded by remember {
        mutableStateOf(false)
    }
    val angle:Float by animateFloatAsState(targetValue = if(expanded) 180f else 0f)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onPrimary
            )
            .clickable { expanded = true },
        verticalAlignment = Alignment.CenterVertically

    ){
        Canvas(modifier = Modifier
            .size(PRIORITY_INDICATOR_SIZE)
            .weight(1f)
            , ){
            drawCircle(color = priority.color)
        }
        Text(
            modifier = Modifier.weight(8f),
            text = priority.name,
        )
        IconButton(
            onClick = {expanded = true},
            modifier = Modifier.weight(1.5f)
                .rotate(degrees = angle)
        ) {
            Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "Dropdown Arrow")
        }

        DropdownMenu(
            modifier = Modifier.fillMaxWidth(fraction = 0.94f),
            expanded = expanded,
            onDismissRequest = {
                expanded = false}

        ) {
            DropdownMenuItem(
                text = { PriorityItem(priority = Priority.LOW) },
                onClick = {
                    expanded = false
                    onPrioritySelected(Priority.LOW)
                }
            )
            DropdownMenuItem(
                text = { PriorityItem(priority = Priority.MEDIUM) },
                onClick = {
                    expanded = false
                    onPrioritySelected(Priority.MEDIUM)
                }
            )
            DropdownMenuItem(
                text = { PriorityItem(priority = Priority.HIGH) },
                onClick = {
                    expanded = false
                    onPrioritySelected(Priority.HIGH)
                }
            )
            DropdownMenuItem(
                text = { PriorityItem(priority = Priority.NONE) },
                onClick = {
                    expanded = false
                    onPrioritySelected(Priority.NONE)
                }
            )
        }
    }
}
@Composable
@Preview
fun PriorityDropDownPreview(){
    PriorityDropDown(priority = Priority.LOW, onPrioritySelected = {})
}