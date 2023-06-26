package com.petito.targetnba.presentation

import androidx.lifecycle.ViewModelProvider
import com.petito.targetnba.BR
import com.petito.targetnba.R
import com.petito.targetnba.ViewModelProviderFactory
import com.petito.targetnba.databinding.ActivityMainBinding
import com.petito.targetnba.presentation.base.BaseActivity
import com.petito.targetnba.presentation.base.BaseViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, BaseViewModel>() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.activity_main

    override val viewModel: BaseViewModel
        get() = ViewModelProvider(this, factory.apply { application })[BaseViewModel::class.java]
}