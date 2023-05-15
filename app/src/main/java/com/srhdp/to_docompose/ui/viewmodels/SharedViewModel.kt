package com.srhdp.to_docompose.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srhdp.to_docompose.data.models.TodoTask
import com.srhdp.to_docompose.data.repositories.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor (private val repository: TodoRepository) :ViewModel(){

    private val _allTasks = MutableStateFlow<List<TodoTask>>(emptyList())
    val allTasks:StateFlow<List<TodoTask>> = _allTasks

    fun getAllTasks(){
        viewModelScope.launch {
            repository.getAllTasks.collect{
                    _allTasks.value = it
            }
        }
    }
}