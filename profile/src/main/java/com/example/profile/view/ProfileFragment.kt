package com.example.profile.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.profile.viewModel.ProfileViewModel
import com.example.profile.R
import com.example.profile.databinding.FragmentProfileBinding
import com.example.utils.extentions.Constants
import com.example.utils.extentions.Constants.TEST_TAG
import com.example.utils.extentions.Helper
import com.example.utils.subject.RxNavigationSubjects

class ProfileFragment : Fragment() {
    companion object {
        const val USER_NAME = "user_name"

        fun newInstance(name: String): ProfileFragment {
            val fragment = ProfileFragment()
            fragment.arguments = Bundle().apply {
                putString(USER_NAME, name)
            }
            return fragment
        }
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

        viewModel.firstNameLiveData.value = arguments?.getString(USER_NAME)

        firstName = arguments?.getString(USER_NAME)

        binding?.profile?.llLogOut?.setOnClickListener {
            Helper.toastShort(context, "Your account has been deleted")
            RxNavigationSubjects.openSignInPage.onNext("")
            viewModel.logOut(firstName)
        }

        binding?.profile?.ivBack?.setOnClickListener {
            activity?.supportFragmentManager
            RxNavigationSubjects.openMainPage.onNext("")
        }

        binding?.apply {
            bnvMain.also {
                it.setOnItemSelectedListener { item ->
                    when (item.itemId) {
                        R.id.btn_home -> {
                            RxNavigationSubjects.openMainPage.onNext("")
                        }
                    }
                    true
                }
            }
        }

        viewModel.firstNameLiveData.observe(viewLifecycleOwner) { binding?.profile?.tvUserName?.text = it }
    }
}