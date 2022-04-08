package com.example.baseproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.example.baseproject.state.ResultState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

abstract class BaseFragment<VM: BaseViewModel, VB: ViewBinding>: Fragment() {
    abstract val viewModel: VM

    protected lateinit var binding: VB

    abstract fun getViewBinding(): VB

    private lateinit var fetchJob: Job

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = getViewBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchJob = viewModel.fetchData()
        observeData()
    }

    abstract fun observeData()

    override fun onDestroyView() {
        super.onDestroyView()
        if (fetchJob.isActive) {
            fetchJob.cancel()
        }
    }

    protected fun <T> Flow<ResultState<T>>.onUiState(
        loading: () -> Unit = {},
        success: (T) -> Unit = {},
        error: (Throwable) -> Unit = {},
        finish: () -> Unit = {}
    ) {
        onResult { state ->
            when (state) {
                ResultState.Loading -> loading()
                is ResultState.Success -> success(state.data)
                is ResultState.Error -> error(state.error)
                ResultState.Finish -> finish()
                else -> Unit
            }
        }
    }

    protected fun <T> Flow<T>.onResult(collect: (T) -> Unit) {
        onEach { collect.invoke(it) }.launchIn(lifecycleScope)
    }

    /* Add Fragment (FrameLayout 아이디, Fragment() 프레그먼트 생성한거) */
    fun addFragment(containerViewId:Int, mFragment: Fragment, backStack: Boolean = false) {
        activity?.let {
            val transaction = it.supportFragmentManager.beginTransaction()
            if(backStack) transaction.addToBackStack(null)
            transaction.replace( containerViewId, mFragment)
            transaction.commitAllowingStateLoss()
        }
    }

    /* Remove Fragment (Fragment() 프레그먼트 생성한거) */
    fun removeFragment(mFragment: Fragment) {
        activity?.let {
            val transaction = it.supportFragmentManager.beginTransaction()
            transaction.remove(mFragment)
            transaction.commitAllowingStateLoss()
        }
    }
}