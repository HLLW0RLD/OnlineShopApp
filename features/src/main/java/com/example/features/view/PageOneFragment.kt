package com.example.features.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.features.databinding.FragmentPageOneBinding
import com.example.features.databinding.FragmentSignInPageBinding

class PageOneFragment : Fragment() {
    companion object{
        fun newInstance() = PageOneFragment()
    }

    var binding : FragmentPageOneBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPageOneBinding.inflate(inflater, container, false)
        return binding!!.root
    }
}