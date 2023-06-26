package com.petito.targetnba.presentation.allteams

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.petito.targetnba.BR
import com.petito.targetnba.R
import com.petito.targetnba.ViewModelProviderFactory
import com.petito.targetnba.databinding.FragmentAllTeamsBinding
import com.petito.targetnba.presentation.base.BaseFragment
import com.petito.targetnba.presentation.base.NavigationCommand
import javax.inject.Inject

class AllTeamsFragment : BaseFragment<FragmentAllTeamsBinding, AllTeamsViewModel>(), AllTeamsAdapterListener {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var allTeamsAdapter: AllTeamsAdapter

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_all_teams

    override val viewModel: AllTeamsViewModel
        get() = ViewModelProvider(requireActivity(), factory)[AllTeamsViewModel::class.java]

    override fun onItemClick(item: AllTeamsDataItem) {
        navigate(
            NavigationCommand.To(
                AllTeamsFragmentDirections.actionAllTeamsFragmentToTeamDetailFragment(item)
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        allTeamsAdapter = AllTeamsAdapter(arrayListOf(), this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        getViewDataBinding().recyclerViewAllTeams.layoutManager = LinearLayoutManager(activity)
        getViewDataBinding().recyclerViewAllTeams.itemAnimator = DefaultItemAnimator()
        getViewDataBinding().recyclerViewAllTeams.adapter = allTeamsAdapter
    }
}