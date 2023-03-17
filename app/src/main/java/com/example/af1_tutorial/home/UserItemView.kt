package com.example.af1_tutorial.home

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.af1_tutorial.R
import com.example.af1_tutorial.data.model.User
import com.example.af1_tutorial.databinding.ItemUserBinding

class UserItemView(private val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(data: User) {
        binding.apply {
            tvName.text = "${data.firstName} ${data.lastName}"
            tvEmail.text = data.email
            Glide.with(binding.root.context)
                .load(data.avatar)
                .placeholder(R.color.purple_200)
                .error(R.color.purple_200)
                .into(civImage)
        }
    }

}