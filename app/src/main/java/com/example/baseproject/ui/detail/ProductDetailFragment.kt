package com.example.baseproject.ui.detail

import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.example.baseproject.R
import com.example.baseproject.data.Response.ProductResponse
import com.example.baseproject.databinding.FragmentProductDetailBinding
import com.example.baseproject.extentions.SingleToast.showToast
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


    override fun observeData() {
        viewModel.productDetailState.onUiState (
            error = { handleError() },
            loading = { handleLoading() },
            success = {
                handleSuccess(it)
            }
         )
    }

    private fun handleLoading() = with(binding) {
        progressBar.isVisible = true
    }

    private fun handleSuccess(data: ProductResponse) = with(binding) {
        progressBar.isGone = true
        val product = data
        productCategoryTextView.text = product.productType
        productImageView.loadCenterCrop(product.productImage, 8f)
        productPriceTextView.text = "${product.productPrice}원"
        introductionImageView.load(product.productImage)
    }

    private fun handleError() {
        showToast("제품 정보를 불러올 수 없습니다.")
    }



}