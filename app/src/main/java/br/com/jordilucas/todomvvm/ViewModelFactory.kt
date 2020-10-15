package br.com.jordilucas.todomvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.jordilucas.todomvvm.database.repository.TodoRepository

class ViewModelFactory(val todoRepository: TodoRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(TodoRepository::class.java).newInstance(todoRepository)
    }
}