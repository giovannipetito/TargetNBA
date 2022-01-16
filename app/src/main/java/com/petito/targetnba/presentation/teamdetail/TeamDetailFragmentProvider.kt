package com.petito.targetnba.presentation.teamdetail

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TeamDetailFragmentProvider {
    @ContributesAndroidInjector
    abstract fun provideTeamDetailFragmentFactory(): TeamDetailFragment
}