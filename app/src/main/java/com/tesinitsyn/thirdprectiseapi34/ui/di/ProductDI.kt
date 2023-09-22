package com.tesinitsyn.thirdprectiseapi34.ui.di

import com.tesinitsyn.thirdprectiseapi34.ui.viewModel.ProductViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var productModel = module {
    viewModel {
        ProductViewModel(get())
    }
}