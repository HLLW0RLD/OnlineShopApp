package com.example.entrance.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.entrance.viewModel.LogInViewModel
import com.example.features.databinding.FragmentLogInBinding
import com.example.utils.extentions.Constants.LOG_IN_TAG
import com.example.utils.extentions.Helper
import com.example.utils.subject.RxNavigationSubjects

class LogInFragment : Fragment() {
    companion object {
        fun newInstance() = LogInFragment()
    }

    var binding: FragmentLogInBinding? = null
    private val viewModel: LogInViewModel by viewModels<LogInViewModel>()

    private var firstName: String? = null
    private var password: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogInBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {

            etFirstName.addTextChangedListener {
                btnLogIn.isEnabled = isFieldsEmpty()
            }

            etPassword.addTextChangedListener {
                btnLogIn.isEnabled = isFieldsEmpty()
            }

            btnLogIn.setOnClickListener {
                viewModel.isUserSaved.observe(viewLifecycleOwner) {
                    checkSavedUser(it)
                }
                viewModel.getUserByName(firstName)
            }

            viewModel.firstNameLiveData.observe(viewLifecycleOwner) { etFirstName.text.toString().replace(" ", "") }
        }
    }

    private fun checkSavedUser(checked: Boolean) {
        if (checked){
            Helper.hideKeyboard(this)
            RxNavigationSubjects.openMainPage.onNext(firstName!!)
            firstName = binding?.etFirstName?.text.toString().replace(" ", "")
        } else {
            Helper.toastShort(context, "User with this name already exist")
        }
    }

    private fun isFieldsEmpty(): Boolean {
        firstName = binding?.etFirstName?.text.toString().replace(" ", "")
        password = binding?.etPassword?.text.toString().replace(" ", "")

        if (firstName?.trim()?.isNotEmpty() == true
            && password?.trim()?.isNotEmpty() == true
        ) {
            Log.d(LOG_IN_TAG, "isFieldsEmpty() fields -> $firstName, $password")
            return true
        }
        return false
    }
}