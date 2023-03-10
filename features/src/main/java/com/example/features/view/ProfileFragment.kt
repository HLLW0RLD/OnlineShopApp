package com.example.features.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.app.utils.Helper
import com.example.app.utils.RxSubjects
import com.example.features.R
import com.example.features.databinding.FragmentProfileBinding
import com.example.features.viewModel.ProfileViewModel

class ProfileFragment : Fragment() {
    companion object {
        fun newInstance() = ProfileFragment()
    }

    var binding: FragmentProfileBinding? = null
    private val viewModel: ProfileViewModel by viewModels<ProfileViewModel>()

    private var firstName: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        RxSubjects.nameData.subscribe {
            viewModel.firstNameLiveData.value = it
            firstName = it
        }

        binding?.profile?.llLogOut?.setOnClickListener {
            Helper.toastShort("Your account has been deleted")
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.container, SignInPageFragment.newInstance())
                ?.commitAllowingStateLoss()
            viewModel.logOut(firstName)
        }

        binding?.profile?.ivBack?.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.add(R.id.container, PageOneFragment.newInstance())
                ?.commitAllowingStateLoss()
        }

        binding?.apply {
            bnvMain.also {
                it.setOnItemSelectedListener { item ->
                    when (item.itemId) {
                        R.id.btn_home -> {
                            activity?.supportFragmentManager
                                ?.beginTransaction()
                                ?.add(R.id.container, PageOneFragment.newInstance())
                                ?.commitAllowingStateLoss()
                        }
                    }
                    true
                }
            }
        }

        viewModel.firstNameLiveData.observe(viewLifecycleOwner) { binding?.profile?.tvUserName?.text = it }
    }
}