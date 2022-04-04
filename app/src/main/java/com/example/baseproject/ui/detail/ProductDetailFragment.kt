package com.example.baseproject.ui.detail

import android.content.Context
import android.content.Intent
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.example.baseproject.databinding.FragmentProductDetailBinding
import com.example.baseproject.databinding.FragmentProductListBinding
import com.example.baseproject.ui.BaseFragment
import com.example.baseproject.ui.list.ProductListViewModel
import com.example.baseproject.utility.load
import com.example.baseproject.utility.loadCenterCrop
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ProductDetailFragment  : BaseFragment<ProductDetailViewModel, FragmentProductDetailBinding>() {

    companion object {

        const val PRODUCT_ID_KEY = "PRODUCT_ID_KEY"

        const val PRODUCT_ORDERED_RESULT_CODE = 99

        fun newIntent(context: Context, productId: Long) =
            Intent(context, ProductDetailFragment::class.java).apply {
                putExtra(PRODUCT_ID_KEY, productId)
            }

    }

    override fun getViewBinding(): FragmentProductDetailBinding =
        FragmentProductDetailBinding.inflate(layoutInflater)

    override val viewModel by viewModel<ProductDetailViewModel> {
        parametersOf(
        )
    }

    override fun observeData() = viewModel.productDetailState.observe(this) {
        when (it) {
            is ProductDetailState.UnInitialized -> initViews()
            is ProductDetailState.Loading -> handleLoading()
            is ProductDetailState.Success -> handleSuccess(it)
            is ProductDetailState.Error ->
        }
    }

    private fun initViews() = with(binding) {
        orderButton.setOnClickListener {
            //viewModel.orderProduct()
        }
    }

    private fun handleLoading() = with(binding) {
        progressBar.isVisible = true
    }

    private fun handleSuccess(state: ProductDetailState.Success) = with(binding) {
        progressBar.isGone = true
        val product = state.productEntity
        productCategoryTextView.text = product.productType
        productImageView.loadCenterCrop(product.productImage, 8f)
        productPriceTextView.text = "${product.productPrice}원"
        introductionImageView.load(state.productEntity.productImage)
    }



}