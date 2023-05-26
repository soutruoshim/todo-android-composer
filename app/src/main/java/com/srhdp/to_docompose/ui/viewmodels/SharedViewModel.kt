package com.srhdp.to_docompose.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srhdp.to_docompose.data.models.Priority
import com.srhdp.to_docompose.data.models.TodoTask
import com.srhdp.to_docompose.data.repositories.TodoRepository
import com.srhdp.to_docompose.util.RequestState
import com.srhdp.to_docompose.util.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor (private val repository: TodoRepository) :ViewModel(){

     val id:MutableState<Int> = mutableStateOf(0)
     val title:MutableState<String> = mutableStateOf("")
     val description:MutableState<String> = mutableStateOf("")
     val priority:MutableState<Priority> = mutableStateOf(Priority.LOW)

     val searchAppBarState: MutableState<SearchAppBarState> = mutableStateOf(SearchAppBarState.CLOSED)
     val searchTextState : MutableState<String>  = mutableStateOf("")


    private val _allTasks = MutableStateFlow<RequestState<List<TodoTask>>>(RequestState.Idle)
    val allTasks:StateFlow<RequestState<List<TodoTask>>> = _allTasks

    fun getAllTasks(){
        _allTasks.value = RequestState.Loading
        try {
            viewModelScope.launch {
                repository.getAllTasks.collect{
                    _allTasks.value = RequestState.Success(it)
                }
            }
        }catch (e:Exception){
           _allTasks.value = RequestState.Error(e)
        }

    }

    private val _selectedTask : MutableStateFlow<TodoTask?> = MutableStateFlow(null)
    val selectedTask: StateFlow<TodoTask?> = _selectedTask
    fun getSelectedTask(taskId: Int){
        viewModelScope.launch {
            repository.getSelectedTask(taskId = taskId).collect{
                _selectedTask.value = it
            }
        }
    }

    fun updateTaskFields(selectedTask:TodoTask?){
        if(selectedTask != null){
            id.value = selectedTask.id
            title.value = selectedTask.title
            description.value = selectedTask.description
            priority.value = selectedTask.priority
        }else{
            id.value = 0
            title.value = ""
            description.value = ""
            priority.value = Priority.LOW
        }
    }
}