package com.example.baseproject.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.baseproject.data.Response.ProductResponse
import com.example.baseproject.databinding.ItemProductLayoutBinding
import com.example.baseproject.utility.loadCenterCrop

class ProductListAdapter : RecyclerView.Adapter<ProductListAdapter.ProductItemViewHolder>() {

    private var productList: List<ProductResponse> = listOf()
    private lateinit var productItemClickListener: (ProductResponse) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemViewHolder {
        val view = ItemProductLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductItemViewHolder(view, productItemClickListener)
    }

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        holder.bindData(productList[position])
        holder.bindViews(productList[position])
    }

    override fun getItemCount(): Int = productList.size

    fun setProductList(productList: List<ProductResponse>, productItemClickListener: (ProductResponse) -> Unit = { }) {
        this.productList = productList
        this.productItemClickListener = productItemClickListener
        notifyDataSetChanged()
    }

    inner class ProductItemViewHolder(
        private val binding: ItemProductLayoutBinding,
        val productItemClickListener: (ProductResponse) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: ProductResponse) = with(binding) {
            productNameTextView.text = data.productName
            productImageView.loadCenterCrop(data.productImage, 8f)
            productPriceTextView.text = "${data.productPrice}원"
        }

        fun bindViews(data: ProductResponse) {
            binding.root.setOnClickListener {
                productItemClickListener(data)
            }
        }
    }

}