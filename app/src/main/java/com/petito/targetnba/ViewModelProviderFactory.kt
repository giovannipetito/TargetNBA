package com.petito.targetnba

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.petito.targetnba.presentation.users.UsersViewModel
import com.petito.targetnba.presentation.user.UserViewModel
import com.petito.targetnba.remote.UsersDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelProviderFactory @Inject constructor(
    private val usersDataSource: UsersDataSource
    ) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(UsersViewModel::class.java) -> {
                UsersViewModel(usersDataSource) as T
            }
            modelClass.isAssignableFrom(UserViewModel::class.java) -> {
                UserViewModel() as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}