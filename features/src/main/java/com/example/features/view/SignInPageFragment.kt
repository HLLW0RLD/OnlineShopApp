package com.example.features.view

import android.os.Bundle
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.app.domain.ILocalService
import com.example.app.utils.Constants.SIGN_IN_TAG
import com.example.app.utils.Helper
import com.example.features.R
import com.example.features.databinding.FragmentSignInPageBinding
import org.koin.android.ext.android.inject

class SignInPageFragment : Fragment() {
    companion object {
        fun newInstance() = SignInPageFragment()
    }

    var binding: FragmentSignInPageBinding? = null
    private val localService: ILocalService by inject()

    private var firstName: String? = null
    private var lastName: String? = null
    private var email: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInPageBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            tvLogin.setOnClickListener {
                activity?.supportFragmentManager?.apply {
                    beginTransaction()
                        .add(R.id.container, PageOneFragment.newInstance())
                        .addToBackStack("")
                        .commitAllowingStateLoss()
                }
            }

            etFirstName.addTextChangedListener {
                btnSignIn.isEnabled = isFieldsEmpty()
            }

            etLastName.addTextChangedListener {
                btnSignIn.isEnabled = isFieldsEmpty()
            }

            etEmail.addTextChangedListener {
                btnSignIn.isEnabled = isFieldsEmpty()
            }

            btnSignIn.setOnClickListener {
               if (isFieldsEmpty()) {
                    if (!checkEmail(email)) {
                        Helper.toastShort("Email address not valid")
                    } else {
                        if (firstName != null && lastName != null && email != null) {
                            localService.insert(firstName!!, lastName!!, email!!)
                            activity?.supportFragmentManager?.apply {
                                beginTransaction()
                                    .add(R.id.container, LogInFragment.newInstance())
                                    .addToBackStack("")
                                    .commitAllowingStateLoss()
                            }
                        }
                    }
                } else {
                   Helper.toastShort("Not all fields are filled")
               }
            }
        }
    }

    private fun isFieldsEmpty(): Boolean{
        firstName = binding?.etFirstName?.text.toString()
        lastName = binding?.etLastName?.text.toString()
        email = binding?.etEmail?.text.toString()

        if (firstName?.trim()?.isNotEmpty() == true
            && lastName?.trim()?.isNotEmpty() == true
            && email?.trim()?.isNotEmpty() == true) {
            Log.d(SIGN_IN_TAG, "isFieldsEmpty() fields -> $firstName, $lastName, $email")
            return true
        }
        return false
    }

    private fun checkEmail(email: String?): Boolean {
        email?.trim()
        Log.d(SIGN_IN_TAG, "checkEmail() email -> $email")
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}