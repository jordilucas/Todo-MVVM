package br.com.jordilucas.todomvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.jordilucas.todomvvm.database.model.Todo
import br.com.jordilucas.todomvvm.database.repository.TodoRepository
import kotlinx.coroutines.launch

class MainViewModel(private val todoRepository: TodoRepository): ViewModel() {

    private val _todos = MutableLiveData<List<Todo>>()
    fun observeTodos (): LiveData<List<Todo>> = _todos

    private val _exception = MutableLiveData<String>()
    fun observeException (): LiveData<String> = _exception

    fun insertNewTask(text: String){
        viewModelScope.launch {
            try{
                val result = todoRepository.insertTask(text)
                _todos.value = result
            }catch (e: Exception){
                _exception.value = e.message
            }
        }
    }

    fun getAllTodos(){
        viewModelScope.launch {
            try{
                _todos.value = todoRepository.getAllTodos()
            }catch (e: Exception){
                _exception.value = e.message
            }
        }
    }

    fun deleteTodo(todo: Todo){
        viewModelScope.launch {
            try{
                _todos.value = todoRepository.deleteTodo(todo)
            }catch (e: Exception){
                _exception.value = e.message
            }
        }
    }

    fun updateTaskMarkComplete(todo: Todo){
        viewModelScope.launch {
            try {
                _todos.value = todoRepository.markComplete(todo)
            }catch (e:java.lang.Exception){
                _exception.value = e.message
            }
        }
    }

}