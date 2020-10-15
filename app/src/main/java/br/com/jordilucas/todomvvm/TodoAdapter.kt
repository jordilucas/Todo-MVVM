package br.com.jordilucas.todomvvm

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import br.com.jordilucas.todomvvm.database.model.Todo
import kotlinx.android.synthetic.main.item_todo_list.view.*

class TodoAdapter(private val itemList: MutableList<Todo>,
        private val listener: AdapterListener,
        private val context: Context): RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_todo_list))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       // holder.bind
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    inner class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        private  lateinit var item: Todo

        fun bindViewHolder(item:Todo){
            this.item = item
            itemView.txtTask.text = item.text
            configCompleteTodos(item)
            itemView.imgComplete.setOnClickListener {
                listener.markWithComplete(item)
            }
            itemView.item_list_container.setOnLongClickListener {
                listener.deleteTodo(item)
                return@setOnLongClickListener true
            }
        }

        private fun configCompleteTodos(todo: Todo){
            if(todo.completed){
                DrawableCompat.setTint(DrawableCompat.wrap(itemView.imgComplete.drawable),
                    ContextCompat.getColor(context, android.R.color.holo_green_light))
            }else{
                DrawableCompat.setTint(DrawableCompat.wrap(itemView.imgComplete.drawable),
                    ContextCompat.getColor(context, android.R.color.holo_red_light))
            }
        }
    }



    interface AdapterListener{
        fun markWithComplete(todo: Todo)
        fun deleteTodo(todo: Todo)
    }
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View{
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}