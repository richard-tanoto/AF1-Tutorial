package com.example.af1_tutorial.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.af1_tutorial.data.model.User
import com.example.af1_tutorial.databinding.ItemUserBinding

class UserAdapter: RecyclerView.Adapter<UserItemView>() {

    private val list: ArrayList<User> = arrayListOf()
    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemView {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserItemView(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: UserItemView, position: Int) {
        val user = list[position]
        holder.bind(user)
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(user) }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun add(users: List<User>) {
        list.addAll(users)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: User)
    }


}