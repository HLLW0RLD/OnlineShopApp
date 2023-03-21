package com.example.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.api.data.ProductDTO
import com.example.home.databinding.ItemRvLatestBinding

class LatestAdapter(val priceStr: String?) : RecyclerView.Adapter<LatestAdapter.MainViewHolder>() {

    private var productData: List<ProductDTO> = listOf()

    fun setData(data: List<ProductDTO>) {
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
        fun bind(product: ProductDTO) {
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
