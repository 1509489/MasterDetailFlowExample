package com.pixelarts.masterdetailflowexample.di

import com.pixelarts.masterdetailflowexample.ui.homescreen.HomeActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun injectHomeScreen(homeActivity: HomeActivity)
}