package com.example.features.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.app.base.BaseApp
import com.example.app.base.Product
import com.example.features.R
import com.example.features.databinding.ItemRvLatestBinding

class LatestAdapter : RecyclerView.Adapter<LatestAdapter.MainViewHolder>() {

    private var productData: List<Product> = listOf()
    private val priceStr = BaseApp.appInstance?.getString(R.string.price_str)

    fun setData(data: List<Product>) {
        productData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemRvLatestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(productData[position])
    }

    override fun getItemCount(): Int {
        return productData.size
    }

    inner class MainViewHolder(val binding: ItemRvLatestBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            if (priceStr != null) {
                val price = String.format(priceStr, product.price.toString())
                binding.apply {
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
