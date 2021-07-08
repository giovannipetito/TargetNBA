package com.petito.targetnba.presentation.allteams

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.petito.targetnba.domain.Result
import com.petito.targetnba.domain.api.AllTeamsResponse
import com.petito.targetnba.presentation.base.BaseViewModel
import com.petito.targetnba.remote.AllTeamsDataSource
import kotlinx.coroutines.launch

class AllTeamsViewModel(
    private val articleDataSource: AllTeamsDataSource
) : BaseViewModel() {
    private val articlesLiveData: MutableLiveData<List<AllTeamsDataItem>> = MutableLiveData()

    private fun fetchArticles(page: Int) {
        viewModelScope.launch {
            isLoading.value = true
            when (val result = articleDataSource.getAllTeams(page)) {
                is Result.Success<AllTeamsResponse> -> {
                    result.data.teams?.let { mapArticlesDataItem(it) }
                    isLoading.value = false
                }
                is Result.Error -> {
                    isLoading.value = false
                    showToast.value = result.message
                }
            }
        }
    }

    val articlesLiveDataLiveData: LiveData<List<AllTeamsDataItem>>
        get() = articlesLiveData

    init {
        fetchArticles(0)
    }

    private fun mapArticlesDataItem(articles: List<AllTeamsResponse.Team>) {
        articlesLiveData.value = articles.map {
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