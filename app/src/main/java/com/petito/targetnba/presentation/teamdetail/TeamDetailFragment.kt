package com.petito.targetnba.presentation.teamdetail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.petito.targetnba.BR
import com.petito.targetnba.R
import com.petito.targetnba.ViewModelProviderFactory
import com.petito.targetnba.databinding.FragmentTeamDetailBinding
import com.petito.targetnba.presentation.allteams.AllTeamsDataItem
import com.petito.targetnba.presentation.base.BaseFragment
import javax.inject.Inject

class TeamDetailFragment :
    BaseFragment<FragmentTeamDetailBinding, TeamDetailViewModel>() {
    @Inject
    lateinit var factory: ViewModelProviderFactory
    private var allTeamsDataItem: AllTeamsDataItem? = null

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_team_detail

    override val viewModel: TeamDetailViewModel
        get() = ViewModelProvider(this, factory).get(TeamDetailViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            allTeamsDataItem = arguments?.getParcelable("allTeamsDataItem")
        }
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        setTeam()
    }

    private fun setTeam() {
        if (allTeamsDataItem != null) {
            getViewDataBinding().allTeamsDataItem = allTeamsDataItem
        }
    }
}