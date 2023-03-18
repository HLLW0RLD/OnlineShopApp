package com.example.app.base.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.entrance.view.LogInFragment
import com.example.entrance.view.SignInPageFragment
import com.example.home.view.PageOneFragment
import com.example.onlineshopapp.R
import com.example.onlineshopapp.databinding.ActivityMainBinding
import com.example.profile.view.ProfileFragment
import com.example.utils.subject.RxNavigationSubjects

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var userName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.apply {
                beginTransaction()
                    .add(R.id.container, SignInPageFragment.newInstance())
                    .commitAllowingStateLoss()
            }
        }

        RxNavigationSubjects.openSignInPage.subscribe {
            openSignInPage()
        }

        RxNavigationSubjects.openLogInPage.subscribe {
            openLogInPage()
        }

        RxNavigationSubjects.openMainPage.subscribe {
            userName = it
            openMainPage()
        }

        RxNavigationSubjects.openProfilePage.subscribe {
            openProfilePage()
        }

    }

    private fun openSignInPage() {
        supportFragmentManager.apply {
            beginTransaction()
                .add(R.id.container, SignInPageFragment.newInstance())
                .addToBackStack("")
                .commitAllowingStateLoss()
        }
    }

    private fun openLogInPage() {
        supportFragmentManager.apply {
            beginTransaction()
                .add(R.id.container, LogInFragment.newInstance())
                .addToBackStack("")
                .commitAllowingStateLoss()
        }
    }

    private fun openMainPage() {
        supportFragmentManager.apply {
            beginTransaction()
                .add(R.id.container, PageOneFragment.newInstance())
                .addToBackStack("")
                .commitAllowingStateLoss()
        }
    }

    private fun openProfilePage() {
        supportFragmentManager.apply {
            beginTransaction()
                .add(R.id.container, ProfileFragment.newInstance())
                .addToBackStack("")
                .commitAllowingStateLoss()
        }
    }
}