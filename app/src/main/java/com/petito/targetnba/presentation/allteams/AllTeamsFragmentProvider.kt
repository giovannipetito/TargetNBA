package com.petito.targetnba.presentation.allteams

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AllTeamsFragmentProvider {
    @ContributesAndroidInjector
    abstract fun provideAllTeamsFragmentFactory(): AllTeamsFragment
}