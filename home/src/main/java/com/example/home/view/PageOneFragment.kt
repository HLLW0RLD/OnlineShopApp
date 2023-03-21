package com.example.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app.utils.hide
import com.example.app.utils.show
import com.example.home.R
import com.example.home.adapter.FlashSalesAdapter
import com.example.home.adapter.LatestAdapter
import com.example.home.databinding.FragmentPageOneBinding
import com.example.home.utils.FlashSalesRvState
import com.example.home.utils.LatestRvState
import com.example.home.viewModel.PageOneViewModel
import com.example.utils.subject.RxNavigationSubjects

class PageOneFragment : Fragment() {
    companion object{
        fun newInstance() = PageOneFragment()
    }

    var binding : FragmentPageOneBinding? = null
    private val latestViewModel: PageOneViewModel by viewModels<PageOneViewModel>()
    private val priceStr = activity?.baseContext?.getString(R.string.price_str)
    private val discountStr = activity?.baseContext?.getString(R.string.discount_str)
    private val latestAdapter = LatestAdapter(priceStr)
    private val flashSalesAdapter = FlashSalesAdapter(priceStr, discountStr)

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
                RxNavigationSubjects.openProfilePage.onNext("")
            }
        }

        binding?.apply {
            bnvMain.also {
                it.setOnItemSelectedListener { item ->
                    when(item.itemId) {
                        R.id.btn_profile -> {
                            RxNavigationSubjects.openProfilePage.onNext("")
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
                RxNavigationSubjects.isAllItemsLoaded.subscribe {
                    if (it.second){
                        binding?.mainPage?.pbLatest?.hide()
                        latestAdapter.setData(data.productData.latest)
                    }
                }
            }
        }
    }

    private fun renderFlashSalesState(data: FlashSalesRvState){
        when(data){
            is FlashSalesRvState.Loading -> {
                binding?.mainPage?.pbFlashSales?.show()
            }
            is FlashSalesRvState.Success -> {
                RxNavigationSubjects.isAllItemsLoaded.subscribe {
                    if (it.first){
                        binding?.mainPage?.pbFlashSales?.hide()
                        flashSalesAdapter.setData(data.productData.flash_sale)
                    }
                }
            }
        }
    }
}

