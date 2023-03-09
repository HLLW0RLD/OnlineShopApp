package com.example.features.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.app.utils.Constants
import com.example.app.utils.Helper
import com.example.features.R
import com.example.features.databinding.FragmentLogInBinding
import com.example.features.viewModel.LogInViewModel

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

            tietPassword.addTextChangedListener {
                btnLogIn.isEnabled = isFieldsEmpty()
            }

            btnLogIn.setOnClickListener {
                firstName = binding?.etFirstName?.text.toString().replace(" ", "")
                viewModel.isUserSaved.observe(viewLifecycleOwner) {
                    checkSavedUser(it)
                }
                viewModel.getUserByName(firstName)
            }
        }
    }

    private fun checkSavedUser(checked: Boolean) {
        if (checked){
            Helper.hideKeyboard(this)
            activity?.supportFragmentManager?.apply {
                beginTransaction()
                    .add(R.id.container, PageOneFragment.newInstance())
                    .addToBackStack("")
                    .commitAllowingStateLoss()
            }
        }
    }

    private fun isFieldsEmpty(): Boolean {
        firstName = binding?.etFirstName?.text.toString().replace(" ", "")
        password = binding?.tietPassword?.text.toString().replace(" ", "")

        if (firstName?.trim()?.isNotEmpty() == true
            && password?.trim()?.isNotEmpty() == true
        ) {
            Log.d(Constants.SIGN_IN_TAG, "isFieldsEmpty() fields -> $firstName, $password")
            return true
        }
        return false
    }
}