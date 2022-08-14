package ru.todo.present.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.todo.present.databinding.WorkItemBinding
import ru.todo.present.ui.viewobject.WorkVo

class WorkAdapter(
    private val clickInfo: (WorkVo) -> Unit,
) : ListAdapter<WorkVo, WorkAdapter.WorkViewHolder>(WorkDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = WorkItemBinding.inflate(layoutInflater, parent, false)
        return WorkViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WorkViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.apply {
            date.text = item.date
            title.text = item.title
            importance.setImageResource(item.image)
            info.setOnClickListener {
                clickInfo.invoke(item)
            }
        }
    }

    override fun onViewRecycled(holder: WorkViewHolder) {
        holder.binding.apply {
            info.setOnClickListener {}
        }
    }

    class WorkViewHolder(val binding: WorkItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }


    class WorkDiff() : DiffUtil.ItemCallback<WorkVo>() {
        override fun areItemsTheSame(oldItem: WorkVo, newItem: WorkVo): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: WorkVo, newItem: WorkVo): Boolean {
            return oldItem == newItem
        }

    }
}