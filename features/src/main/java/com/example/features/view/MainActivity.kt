package com.example.features.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.features.R
import com.example.features.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        testFunForCheck()
        if (savedInstanceState == null) {
            supportFragmentManager.apply {
                beginTransaction()
                    .add(R.id.container, SignInPageFragment.newInstance())
//                    .addToBackStack("")
                    .commitAllowingStateLoss()
            }
        }
    }

//    private fun testFunForCheck() {
//        repo
//            .getLatest()
//            .subscribeBy(
//                onSuccess = {
//                    Log.d("RepoTag", it.latest.toString())
//                }
//            )
//    }
}