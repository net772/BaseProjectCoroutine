package com.example.baseproject.ui.detail

import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.example.baseproject.databinding.FragmentProductDetailBinding
import com.example.baseproject.ui.BaseFragment
import com.example.baseproject.utility.load
import com.example.baseproject.utility.loadCenterCrop
import com.example.baseproject.utility.toast
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ProductDetailFragment : BaseFragment<ProductDetailViewModel, FragmentProductDetailBinding>() {

    companion object {
        const val PRODUCT_ID_KEY = "PRODUCT_ID_KEY"
    }


    override val viewModel by viewModel<ProductDetailViewModel> {
        parametersOf(
            arguments?.getString(PRODUCT_ID_KEY)
        )
    }

    override fun getViewBinding(): FragmentProductDetailBinding = FragmentProductDetailBinding.inflate(layoutInflater)


    override fun observeData() = viewModel.productDetailState.observe(this) {
        when (it) {
            is ProductDetailState.Success -> handleSuccess(it)
            is ProductDetailState.Error ->handleError()
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

    private fun handleError() {
        requireContext().toast("제품 정보를 불러올 수 없습니다.")
    }



}