package com.example.profile.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.profile.viewModel.ProfileViewModel
import com.example.profile.R
import com.example.profile.databinding.FragmentProfileBinding
import com.example.utils.extentions.Helper
import com.example.utils.subject.RxNavigationSubjects

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

        // TODO replace to args
//        RxSubjects.nameData.subscribe {
//            viewModel.firstNameLiveData.value = it
//            firstName = it
//        }

        binding?.profile?.llLogOut?.setOnClickListener {
//            val context = activity?.applicationContext
            Helper.toastShort(context, "Your account has been deleted")
            // TODO open sign in
            RxNavigationSubjects.openSignInPage.onNext("")
            viewModel.logOut(firstName)
        }

        binding?.profile?.ivBack?.setOnClickListener {
            activity?.supportFragmentManager
                // TODO open main page
            RxNavigationSubjects.openMainPage.onNext("")
        }

        binding?.apply {
            bnvMain.also {
                it.setOnItemSelectedListener { item ->
                    when (item.itemId) {
                        R.id.btn_home -> {
                            // TODO open main page
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