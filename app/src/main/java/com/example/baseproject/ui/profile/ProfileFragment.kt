package com.example.baseproject.ui.profile

import com.example.baseproject.databinding.FragmentProfileBinding
import com.example.baseproject.ui.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileFragment : BaseFragment<ProfileViewModel, FragmentProfileBinding>() {

    override val viewModel by viewModel<ProfileViewModel>()

    override fun getViewBinding(): FragmentProfileBinding =
        FragmentProfileBinding.inflate(layoutInflater)

    override fun observeData() {
        TODO("Not yet implemented")
    }

}