package com.example.features.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.app.base.BaseApp
import com.example.app.base.Product
import com.example.features.R
import com.example.features.databinding.ItemRvFlashSalesBinding

class FlashSalesAdapter: RecyclerView.Adapter<FlashSalesAdapter.MainViewHolder>() {

    private var productData: List<Product> = listOf()
    private val priceStr = BaseApp.appInstance?.getString(R.string.price_str)
    private val discountStr = BaseApp.appInstance?.getString(R.string.discount_str)

    fun setData(data: List<Product>) {
        productData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemRvFlashSalesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(productData[position])
    }

    override fun getItemCount(): Int {
        return productData.size
    }

    inner class MainViewHolder(val binding: ItemRvFlashSalesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            if (priceStr != null && discountStr != null) {
                val price = String.format(priceStr, product.price.toString())
                val discount = String.format(discountStr, product.discount.toString())
                binding.apply {
                    tvDiscount.text = discount
                    tvPrice.text = price
                    tvCategory.text = product.category
                    tvName.text = product.name
                    Glide
                        .with(root)
                        .load(product.image_url)
                        .into(ivPhoto)
                }
            }
        }
    }
}
