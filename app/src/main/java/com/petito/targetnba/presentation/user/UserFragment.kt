package com.petito.targetnba.presentation.user

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.petito.targetnba.BR
import com.petito.targetnba.R
import com.petito.targetnba.databinding.FragmentUserBinding
import com.petito.targetnba.domain.api.User
import com.petito.targetnba.presentation.base.BaseFragment

class UserFragment : BaseFragment<FragmentUserBinding, UserViewModel>() {

    private var user: User? = null

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_user

    override val viewModel: UserViewModel get() = ViewModelProvider(requireActivity(), mainActivity.factory)[UserViewModel::class.java]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            // user = arguments?.getParcelable("usersDataItem")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTeam()
    }

    private fun setTeam() {
        if (user != null) {
            getViewDataBinding().user = user
        }
    }
}