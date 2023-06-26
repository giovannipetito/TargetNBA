package com.petito.targetnba

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.petito.targetnba.presentation.allteams.AllTeamsViewModel
import com.petito.targetnba.presentation.teamdetail.TeamDetailViewModel
import com.petito.targetnba.remote.AllTeamsDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelProviderFactory @Inject constructor(
    private val allTeamsDataSource: AllTeamsDataSource
    ) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AllTeamsViewModel::class.java) -> {
                AllTeamsViewModel(allTeamsDataSource) as T
            }
            modelClass.isAssignableFrom(TeamDetailViewModel::class.java) -> {
                TeamDetailViewModel() as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}