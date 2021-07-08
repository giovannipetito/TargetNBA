package com.petito.targetnba.presentation.base

import androidx.lifecycle.ViewModel
import com.petito.targetnba.utils.SingleLiveEvent

abstract class BaseViewModel : ViewModel() {
    val isLoading: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val showToast: SingleLiveEvent<String> = SingleLiveEvent()
}