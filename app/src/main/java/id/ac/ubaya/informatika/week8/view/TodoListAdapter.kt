package id.ac.ubaya.informatika.week8.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.week8.R
import id.ac.ubaya.informatika.week8.model.Todo
import id.ac.ubaya.informatika.week8.viewmodel.DetailTodoViewModel
import kotlinx.android.synthetic.main.fragment_create_todo.*
import kotlinx.android.synthetic.main.todo_item_layout.view.*

class TodoListAdapter(val todoList:ArrayList<Todo>, val adapterOnClick:(Any)->Unit):RecyclerView.Adapter<TodoListAdapter.TodoListViewHolder>() {
    class TodoListViewHolder(var view:View):RecyclerView.ViewHolder(view)

    fun updateTodoList(newTodoListAdapter:List<Todo>){
        todoList.clear()
        todoList.addAll(newTodoListAdapter)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.todo_item_layout, parent, false)

        return TodoListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {
        holder.view.checkTask.text = todoList[position].title +" "+todoList[position].is_done

        holder.view.imgEdit.setOnClickListener {
            val action = TodoListfragmentDirections.actionEditTodoFragment(todoList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }

        holder.view.checkTask.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked == true) {
                adapterOnClick(todoList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

}