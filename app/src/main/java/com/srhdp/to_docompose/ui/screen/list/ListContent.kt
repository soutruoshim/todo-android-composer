package com.srhdp.to_docompose.ui.screen.list

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.srhdp.to_docompose.data.models.Priority
import com.srhdp.to_docompose.data.models.TodoTask
import com.srhdp.to_docompose.ui.theme.LARGE_PADDING
import com.srhdp.to_docompose.ui.theme.PRIORITY_INDICATOR_SIZE
import com.srhdp.to_docompose.util.RequestState
import com.srhdp.to_docompose.util.SearchAppBarState

@ExperimentalMaterial3Api
@Composable
fun ListContent(
    allTasks: RequestState<List<TodoTask>>,
    searchedTasks: RequestState<List<TodoTask>>,
    lowPriorityTasks: List<TodoTask>,
    highPriorityTasks: List<TodoTask>,
    sortState: RequestState<Priority>,
    searchAppBarState: SearchAppBarState,
    navigateToTaskScreen:(taskId:Int)->Unit
){
    if(sortState is RequestState.Success){
        when{
            searchAppBarState == SearchAppBarState.TRIGGERED -> {
                if (searchedTasks is RequestState.Success) {
                    HandleListContent(
                        tasks = searchedTasks.data,
                        navigateToTaskScreen = navigateToTaskScreen
                    )
                }
            }
            sortState.data == Priority.NONE -> {
                if(allTasks is RequestState.Success){
                    HandleListContent(tasks = allTasks.data, navigateToTaskScreen = navigateToTaskScreen)
                }
            }
            sortState.data == Priority.LOW -> {
                    HandleListContent(tasks = lowPriorityTasks, navigateToTaskScreen = navigateToTaskScreen)
            }
            sortState.data == Priority.HIGH -> {
                    HandleListContent(tasks = highPriorityTasks, navigateToTaskScreen = navigateToTaskScreen)
            }
        }




    }

}
@Composable
fun HandleListContent(
    tasks:List<TodoTask>,
    navigateToTaskScreen: (taskId: Int) -> Unit
){
    if(tasks.isEmpty()){
        EmptyContent()
    }else{
        DisplayTasks(tasks = tasks, navigateToTaskScreen = navigateToTaskScreen)
    }
}
@Composable
fun DisplayTasks(  tasks: List<TodoTask>,
                   navigateToTaskScreen:(taskId:Int)->Unit){
    LazyColumn{
        items(
            items = tasks,
            key = {task ->
                task.id
            }
        ){
                task ->
            TaskItem(todoTask = task, navigateToTaskScreen = navigateToTaskScreen)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskItem(
    todoTask: TodoTask,
    navigateToTaskScreen:(taskId:Int)->Unit
){
    Surface(modifier = Modifier
        .fillMaxWidth(),
        shape = RectangleShape,
        onClick = {
            navigateToTaskScreen(todoTask.id)
        }
        ) {
        Column(modifier = Modifier
            .padding(all = LARGE_PADDING)
            .fillMaxWidth()
        ) {
            Row{
                Text(
                    modifier = Modifier.weight(8f),
                    text = todoTask.title,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.TopEnd

                ){

                    Canvas(
                        modifier = Modifier
                            .width(PRIORITY_INDICATOR_SIZE)
                            .height(PRIORITY_INDICATOR_SIZE),
                    ){
                        drawCircle(
                            color = todoTask.priority.color
                        )
                    }
                }
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = todoTask.description,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
@ExperimentalMaterial3Api
@Composable
@Preview
fun TaskItemPreview() {
    TaskItem(
        todoTask = TodoTask(0, "Title", "Som Random text", priority = Priority.HIGH),
        navigateToTaskScreen = {}
    )
}