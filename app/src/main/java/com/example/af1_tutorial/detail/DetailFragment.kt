package com.example.af1_tutorial.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.af1_tutorial.R
import com.example.af1_tutorial.databinding.FragmentDetailBinding

class DetailFragment: Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = DetailFragmentArgs.fromBundle(arguments as Bundle).user
        binding.apply {
            tvName.text = "${user.firstName} ${user.lastName}"
            tvEmail.text = user.email
            tvId.text = "ID: ${user.id}"
            Glide.with(requireContext())
                .load(user.avatar)
                .placeholder(R.color.purple_200)
                .error(R.color.purple_200)
                .into(civImage)
        }
    }

}