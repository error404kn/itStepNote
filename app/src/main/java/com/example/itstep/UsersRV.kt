package com.example.itstep

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemLongClickListener
import androidx.recyclerview.widget.RecyclerView
import com.example.itstep.databinding.SingleItemBinding

class UsersRV(): RecyclerView.Adapter<UsersRV.UserViewHolder>() {

    var itemCallback: ((String) -> Unit)? = null

    private var noteList = emptyList<Note>()
    var onItemLongClickListener: ((Note) -> Unit)? = null

    inner class UserViewHolder(
        private val binding: SingleItemBinding,
    ): RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnLongClickListener{
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION){
                    onItemLongClickListener?.invoke(noteList[position])
                }
                true
            }
        }
        fun bind() {
            val item = getItem(adapterPosition)

            binding.apply {
                titleTV.text = item.title
                noteTV.text = item.note

                root.setOnClickListener {
                    itemCallback?.invoke(item.title)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            SingleItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = noteList.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind()
    }

    private fun getItem(position: Int): Note {
        return noteList[position]
    }

    fun updateList(newList: List<Note>) {
        noteList = newList
        notifyDataSetChanged()
    }
}