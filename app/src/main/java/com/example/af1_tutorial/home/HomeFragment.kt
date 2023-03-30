package com.example.af1_tutorial.home

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.af1_tutorial.R
import com.example.af1_tutorial.data.model.User
import com.example.af1_tutorial.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar

class HomeFragment: Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var userAdapter: UserAdapter? = null
    private val viewModel: HomeViewModel by viewModels()

    private var page: Int = 1
    private val menuProvider = object: MenuProvider {
        override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
            menuInflater.inflate(R.menu.option_menu, menu)
        }

        override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
            return when (menuItem.itemId) {
                R.id.btnSearch -> {
                    //Move to search screen
                    true
                }
                R.id.btnFavorite -> {
                    //Move to favorite screen
                    true
                }
                R.id.btnSetting -> {
                    //Move to setting screen
                    true
                }
                else -> false
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupOptionsMenu()
        setupRecyclerView()
        setupViewModelObserver()
    }

    private fun setupOptionsMenu() {
        requireActivity().removeMenuProvider(object: MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.option_menu, menu)
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean { return false }
        })
        requireActivity().addMenuProvider(menuProvider, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun setupRecyclerView() {
        page = 1
        userAdapter = UserAdapter()
        binding.rvUser.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = userAdapter
        }
        userAdapter?.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {
                view?.findNavController()?.navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(data))
            }
        })
    }

    private fun setupViewModelObserver() {
        viewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        viewModel.userList.observe(viewLifecycleOwner) {
            userAdapter?.add(it)
            userAdapter?.notifyDataSetChanged()
            page += 1
            viewModel.getUsers(page)
        }

        viewModel.isEmpty.observe(viewLifecycleOwner) {
            showEmpty(it)
        }

        viewModel.isError.observe(viewLifecycleOwner) {
            showError(it)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.pbLoading.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showEmpty(isEmpty: Boolean) {
       if (isEmpty) Snackbar.make(binding.root, "No users found", Snackbar.LENGTH_LONG).show()
    }

    private fun showError(isError: Boolean) {
        binding.btnRetry.visibility = if (isError) View.VISIBLE else View.GONE
        if (isError) Snackbar.make(binding.root, "Something's wrong. Please try again", Snackbar.LENGTH_LONG).show()
        //if (isError) Toast.makeText(context, "Something's wrong. Please try again", Toast.LENGTH_LONG).show()
    }

    /*override fun onItemClicked(data: User) {
        view?.findNavController()?.navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(data))
    }*/

    override fun onPause() {
        super.onPause()
        //requireActivity().removeMenuProvider(menuProvider)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}