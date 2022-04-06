package com.example.baseproject.ui.list

import android.os.Bundle
import androidx.core.view.isGone
import com.example.baseproject.R
import com.example.baseproject.data.Response.ProductResponse
import com.example.baseproject.databinding.FragmentProductListBinding
import com.example.baseproject.extentions.SingleToast.showToast
import com.example.baseproject.ui.BaseFragment
import com.example.baseproject.ui.adapter.ProductListAdapter
import com.example.baseproject.ui.detail.ProductDetailFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductListFragment : BaseFragment<ProductListViewModel, FragmentProductListBinding>() {
    companion object {
        const val TAG = "ProductListFragment"
    }

    private val adapter = ProductListAdapter()
    private val productDetailFragment by lazy {
        ProductDetailFragment()
    }

    override val viewModel by viewModel<ProductListViewModel>()

    override fun getViewBinding(): FragmentProductListBinding = FragmentProductListBinding.inflate(layoutInflater)


    override fun observeData() {
        viewModel.productListStateLiveData.onUiState (
            loading = {
                initViews()
            },
            error = {
                showToast(R.string.network_error)
            },
            success = { data ->
                handleSuccessState(data)
            }
        )
    }

    private fun initViews() = with(binding) {
        recyclerView.adapter = adapter

        refreshLayout.setOnRefreshListener {
            viewModel.fetchData()
        }
    }

    private fun handleSuccessState(data: List<ProductResponse>) = with(binding) {
        refreshLayout.isEnabled = data.isNotEmpty()
        refreshLayout.isRefreshing = false

        if (data.isEmpty()) {
            emptyResultTextView.isGone = false
            recyclerView.isGone = true
        } else {
            emptyResultTextView.isGone = true
            recyclerView.isGone = false
            adapter.setProductList(data) { list ->
                val bundle = Bundle().also {
                    it.putString("PRODUCT_ID_KEY", list.id)
                }
                productDetailFragment.arguments = bundle
                addFragment(R.id.fragmentContainer, productDetailFragment, true)
            }
        }
    }
}