package com.petito.targetnba.presentation.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.petito.targetnba.domain.Result
import com.petito.targetnba.domain.api.User
import com.petito.targetnba.domain.api.UsersResponse
import com.petito.targetnba.presentation.base.BaseViewModel
import com.petito.targetnba.remote.UsersDataSource
import kotlinx.coroutines.launch

class UsersViewModel(private val usersDataSource: UsersDataSource) : BaseViewModel() {

    private val _users: MutableLiveData<List<User>> = MutableLiveData()
    val users: LiveData<List<User>>
        get() = _users

    init {
        fetchTeams(0)
    }

    private fun fetchTeams(page: Int) {
        viewModelScope.launch {
            isLoading.value = true
            when (val result: Result<UsersResponse> = usersDataSource.getUsers(page)) {
                is Result.Success<UsersResponse> -> {
                    result.data.users?.let {
                        _users.value = it
                        isLoading.value = false
                    }
                }
                is Result.Error -> {
                    isLoading.value = false
                    showToast.value = result.message
                }
            }
        }
    }
}