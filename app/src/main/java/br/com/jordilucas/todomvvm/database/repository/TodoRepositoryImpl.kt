package br.com.jordilucas.todomvvm.database.repository

import br.com.jordilucas.todomvvm.database.model.Todo
import br.com.jordilucas.todomvvm.database.source.TodoDao

interface TodoRepository{
    suspend fun insertTask(text: String): List<Todo>
    suspend fun getAllTodos(): List<Todo>
    suspend fun markComplete(todo: Todo): List<Todo>
    suspend fun deleteTodo(todo: Todo): List<Todo>
}

class TodoRepositoryImpl(private val todoDao: TodoDao): TodoRepository {
    override suspend fun insertTask(text: String): List<Todo> {
        val todo = Todo(text = text)
        todoDao.addItem(todo)
        return getAllTodos()
    }

    override suspend fun getAllTodos() = todoDao.getItems()

    override suspend fun markComplete(todo: Todo): List<Todo> {
        todoDao.markCompleted(todo.id)
        return getAllTodos()
    }

    override suspend fun deleteTodo(todo: Todo): List<Todo> {
        todoDao.deleteTodo(todo)
        return getAllTodos()
    }
}