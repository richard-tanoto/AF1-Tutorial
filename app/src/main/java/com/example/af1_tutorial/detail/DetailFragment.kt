package com.example.af1_tutorial.detail

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.bumptech.glide.Glide
import com.example.af1_tutorial.R
import com.example.af1_tutorial.databinding.FragmentDetailBinding

class DetailFragment: Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val menuProvider = object: MenuProvider {
        override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
            menuInflater.inflate(R.menu.option_menu, menu)
            menu.findItem(R.id.btnSearch).isVisible = false
            menu.findItem(R.id.btnSetting).isVisible = false
        }
        override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
            //Since menuItem is just one, move to favorite screen
            return true
        }
    }

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

        (requireActivity() as MenuHost).addMenuProvider(menuProvider, viewLifecycleOwner, Lifecycle.State.RESUMED)

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

    override fun onPause() {
        super.onPause()
    }

}