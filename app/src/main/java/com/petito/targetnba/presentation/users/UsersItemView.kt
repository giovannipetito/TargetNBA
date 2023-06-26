package com.petito.targetnba.presentation.users

class UsersItemView(private val onItemClick: () -> Unit) {
    fun onItemClick() = onItemClick.invoke()
}