package com.example.baseproject.ui.main

import com.example.baseproject.ui.BaseViewModel
import kotlinx.coroutines.Job

class MainViewModel: BaseViewModel() {
    override fun fetchData(): Job = Job()
}