package com.example.af1_tutorial.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.af1_tutorial.authentication.AuthenticationFragmentDirections
import com.example.af1_tutorial.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setActionClick()
    }

    private fun setActionClick() {
        binding.btnLogin.setOnClickListener {
            view?.findNavController()?.navigate(AuthenticationFragmentDirections.actionAuthenticationFragmentToHomeFragment())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}