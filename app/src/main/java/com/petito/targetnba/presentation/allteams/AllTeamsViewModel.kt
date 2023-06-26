package com.petito.targetnba.presentation.allteams

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.petito.targetnba.domain.Result
import com.petito.targetnba.domain.api.AllTeamsResponse
import com.petito.targetnba.presentation.base.BaseViewModel
import com.petito.targetnba.remote.AllTeamsDataSource
import kotlinx.coroutines.launch

class AllTeamsViewModel(private val allTeamsDataSource: AllTeamsDataSource) : BaseViewModel() {

    private val listMLD: MutableLiveData<List<AllTeamsDataItem>> = MutableLiveData()
    val listLD: LiveData<List<AllTeamsDataItem>>
        get() = listMLD

    init {
        fetchTeams(0)
    }

    private fun fetchTeams(page: Int) {
        viewModelScope.launch {
            isLoading.value = true
            when (val result: Result<AllTeamsResponse> = allTeamsDataSource.getAllTeams(page)) {
                is Result.Success<AllTeamsResponse> -> {
                    result.data.teams?.let { mapTeamsDataItem(it) }
                    isLoading.value = false
                }
                is Result.Error -> {
                    isLoading.value = false
                    showToast.value = result.message
                }
            }
        }
    }

    private fun mapTeamsDataItem(teams: List<AllTeamsResponse.Team>) {
        listMLD.value = teams.map {
            AllTeamsDataItem(
                it.id!!,
                it.abbreviation,
                it.city,
                it.conference,
                it.division,
                it.full_name,
                it.name
            )
        }
    }
}