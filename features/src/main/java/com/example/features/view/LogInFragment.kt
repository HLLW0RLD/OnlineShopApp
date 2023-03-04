package com.example.features.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.features.databinding.FragmentLogInBinding

class LogInFragment : Fragment() {
    companion object {
        fun newInstance() = LogInFragment()
    }

    var binding: FragmentLogInBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogInBinding.inflate(inflater, container, false)
        return binding!!.root
    }
}