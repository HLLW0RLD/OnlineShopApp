package com.example.features.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.app.utils.Constants.SIGN_IN_TAG
import com.example.app.utils.Helper
import com.example.features.R
import com.example.features.databinding.FragmentSignInPageBinding
import com.example.features.viewModel.SignInViewModel

class SignInPageFragment : Fragment() {
    companion object {
        fun newInstance() = SignInPageFragment()
    }

    private var binding: FragmentSignInPageBinding? = null
    private val viewModel: SignInViewModel by viewModels<SignInViewModel>()

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
                        .add(R.id.container, LogInFragment.newInstance())
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
                            viewModel.isUserSaved.observe(viewLifecycleOwner) {
                                checkSavedUser(it)
                            }
                            viewModel.getUserByName(firstName)
                        }
                    }
                } else {
                    Helper.toastShort("Not all fields are filled")
                }
            }
            viewModel.firstNameLiveData.observe(viewLifecycleOwner) { etFirstName.text }
            viewModel.lastNameLiveData.observe(viewLifecycleOwner) { etLastName.text }
            viewModel.emailLiveData.observe(viewLifecycleOwner) { etEmail.text }
        }
    }

    private fun checkSavedUser(checked: Boolean) {
        if (!checked){
            Log.d(SIGN_IN_TAG, "checkSavedUser() user not exist")
            Helper.hideKeyboard(this)
            viewModel.saveUserData(firstName, lastName, email)
            activity?.supportFragmentManager?.apply {
                beginTransaction()
                    .add(R.id.container, PageOneFragment.newInstance())
                    .addToBackStack("")
                    .commitAllowingStateLoss()
            }
        }
    }

    private fun isFieldsEmpty(): Boolean{
        firstName = binding?.etFirstName?.text.toString().replace(" ", "")
        lastName = binding?.etLastName?.text.toString().replace(" ", "")
        email = binding?.etEmail?.text.toString().replace(" ", "")

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