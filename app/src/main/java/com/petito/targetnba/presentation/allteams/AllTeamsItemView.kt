package com.petito.targetnba.presentation.allteams

class AllTeamsItemView(private val onItemClick: () -> Unit) {
    fun onItemClick() = onItemClick.invoke()
}