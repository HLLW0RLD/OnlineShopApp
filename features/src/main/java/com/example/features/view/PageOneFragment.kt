package com.example.features.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app.utils.hide
import com.example.app.utils.show
import com.example.features.R
import com.example.features.adapter.FlashSalesAdapter
import com.example.features.adapter.LatestAdapter
import com.example.features.databinding.FragmentPageOneBinding
import com.example.features.utils.FlashSalesRvState
import com.example.features.utils.LatestRvState
import com.example.features.viewModel.PageOneViewModel

class PageOneFragment : Fragment() {
    companion object{
        fun newInstance() = PageOneFragment()
    }

    var binding : FragmentPageOneBinding? = null
    private val latestViewModel: PageOneViewModel by viewModels<PageOneViewModel>()
    private val latestAdapter = LatestAdapter()
    private val flashSalesAdapter = FlashSalesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPageOneBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.mainPage?.apply {
            rvLatest.adapter = latestAdapter
            rvFlashSales.adapter = flashSalesAdapter

            rvLatest.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            rvFlashSales.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            rvBrands.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            svAvatar.setOnClickListener {
                activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.add(R.id.container, ProfileFragment.newInstance())
                    ?.addToBackStack("")
                    ?.commitAllowingStateLoss()
            }
        }

        binding?.apply {
            bnvMain.also {
                it.setOnItemSelectedListener { item ->
                    when(item.itemId) {
                        R.id.btn_profile -> {
                            activity?.supportFragmentManager
                                ?.beginTransaction()
                                ?.add(R.id.container, ProfileFragment.newInstance())
                                ?.commitAllowingStateLoss()
                        }
                    }
                    true
                }
            }
        }

        latestViewModel.latest.observe(viewLifecycleOwner) { renderLatestState(it) }
        latestViewModel.getLatest()

        latestViewModel.flashSales.observe(viewLifecycleOwner) { renderFlashSalesState(it) }
        latestViewModel.getFlashSales()
    }

    private fun renderLatestState(data: LatestRvState){
        when(data){
            is LatestRvState.Loading -> {
                binding?.mainPage?.pbLatest?.show()
            }
            is LatestRvState.Success -> {
                binding?.mainPage?.pbLatest?.hide()
                latestAdapter.setData(data.productData)
            }
        }
    }

    private fun renderFlashSalesState(data: FlashSalesRvState){
        when(data){
            is FlashSalesRvState.Loading -> {
                binding?.mainPage?.pbFlashSales?.show()
            }
            is FlashSalesRvState.Success -> {
                binding?.mainPage?.pbFlashSales?.hide()
                flashSalesAdapter.setData(data.productData)
            }
        }
    }
}

