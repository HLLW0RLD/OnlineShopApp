package com.example.features.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.features.adapter.FlashSalesAdapter
import com.example.features.adapter.LatestAdapter
import com.example.features.databinding.FragmentPageOneBinding
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

        binding?.apply {
            mainPage.rvLatest.adapter = latestAdapter
            mainPage.rvFlashSales.adapter = flashSalesAdapter

            mainPage.rvLatest.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            mainPage.rvFlashSales.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            mainPage.rvBrands.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        latestViewModel.latest.observe(viewLifecycleOwner) { latestAdapter.setData(it) }
        latestViewModel.getLatest()

        latestViewModel.flashSales.observe(viewLifecycleOwner) { flashSalesAdapter.setData(it) }
        latestViewModel.getFlashSales()
    }
}