package com.example.baseproject.ui.list

import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isGone
import com.example.baseproject.databinding.FragmentProductListBinding
import com.example.baseproject.ui.BaseFragment
import com.example.baseproject.ui.adapter.ProductListAdapter
import com.example.baseproject.ui.profile.ProfileViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ProductListFragment : BaseFragment<ProductListViewModel, FragmentProductListBinding>() {
    companion object {
        const val TAG = "ProductListFragment"
    }

    private val adapter = ProductListAdapter()

    override val viewModel by viewModel<ProductListViewModel>()

    private val startProductDetailForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->

        }

    override fun getViewBinding(): FragmentProductListBinding = FragmentProductListBinding.inflate(layoutInflater)


    override fun observeData() {
        viewModel.productListStateLiveData.observe(this) {
            when (it) {
                is ProductListState.UnInitialized -> {
                    initViews()
                }

                is ProductListState.Success -> {
                    handleSuccessState(it)
                }
            }
        }
    }

    private fun initViews() = with(binding) {
        recyclerView.adapter = adapter

        refreshLayout.setOnRefreshListener {
            viewModel.fetchData()
        }
    }

    private fun handleSuccessState(state: ProductListState.Success) = with(binding){
        refreshLayout.isEnabled = state.productList.isNotEmpty()
        refreshLayout.isRefreshing = false

        if (state.productList.isEmpty()) {
            emptyResultTextView.isGone = false
            recyclerView.isGone = true
        } else {
            emptyResultTextView.isGone = true
            recyclerView.isGone = false
            adapter.setProductList(state.productList) {
                startProductDetailForResult.launch(
                   // ProductDetailActivity.newIntent(requireContext(), it.id)
                )
            }
        }
    }
}