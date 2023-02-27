package com.example.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.api.remote.ProductApi
import com.example.api.remote.ProductInterface
import com.example.api.repository.IRemoteRepository
import com.example.api.repository.RemoteRepository
import com.example.features.view.SignInPageFragment
import com.example.onlineshopapp.R
import com.example.onlineshopapp.databinding.ActivityMainBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.apply {
                beginTransaction()
                    .add(R.id.container, SignInPageFragment.newInstance())
                    .addToBackStack("")
                    .commitAllowingStateLoss()
            }
        }
    }
}