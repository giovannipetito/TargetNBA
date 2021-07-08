package com.petito.targetnba.di

import com.petito.targetnba.presentation.MainActivity
import com.petito.targetnba.presentation.allteams.AllTeamsFragmentProvider
import com.petito.targetnba.presentation.teamdetail.TeamDetailFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [AllTeamsFragmentProvider::class, TeamDetailFragmentProvider::class])
    abstract fun bindMainActivity(): MainActivity
}