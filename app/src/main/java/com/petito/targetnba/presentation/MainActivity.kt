package com.petito.targetnba.presentation

import androidx.lifecycle.ViewModelProvider
import com.petito.targetnba.BR
import com.petito.targetnba.R
import com.petito.targetnba.ViewModelProviderFactory
import com.petito.targetnba.databinding.ActivityMainBinding
import com.petito.targetnba.presentation.base.BaseActivity
import com.petito.targetnba.presentation.base.BaseViewModel
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, BaseViewModel>(),
    HasAndroidInjector {
    @Inject
    lateinit var factory: ViewModelProviderFactory

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.activity_main

    override val viewModel: BaseViewModel
        get() = ViewModelProvider(this, factory).get(BaseViewModel::class.java)
}