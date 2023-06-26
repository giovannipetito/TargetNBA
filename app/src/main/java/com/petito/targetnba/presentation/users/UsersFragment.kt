package com.petito.targetnba.presentation.users

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.petito.targetnba.BR
import com.petito.targetnba.R
import com.petito.targetnba.databinding.FragmentUsersBinding
import com.petito.targetnba.domain.api.User
import com.petito.targetnba.presentation.base.BaseFragment
import com.petito.targetnba.presentation.base.NavigationCommand

class UsersFragment : BaseFragment<FragmentUsersBinding, UsersViewModel>(), UsersAdapterListener {

    private lateinit var usersAdapter: UsersAdapter

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_users

    override val viewModel: UsersViewModel get() = ViewModelProvider(requireActivity(), mainActivity.factory)[UsersViewModel::class.java]

    override fun onItemClick(user: User) {
        navigate(
            NavigationCommand.To(
                AllTeamsFragmentDirections.actionAllTeamsFragmentToTeamDetailFragment(user)
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        usersAdapter = UsersAdapter(arrayListOf(), this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        getViewDataBinding().recyclerViewAllTeams.layoutManager = LinearLayoutManager(activity)
        getViewDataBinding().recyclerViewAllTeams.itemAnimator = DefaultItemAnimator()
        getViewDataBinding().recyclerViewAllTeams.adapter = usersAdapter
    }
}