package com.pixelarts.masterdetailflowexample.di

import androidx.fragment.app.Fragment
import com.pixelarts.masterdetailflowexample.di.fragment.FragmentComponent
import com.pixelarts.masterdetailflowexample.di.fragment.FragmentModule
import com.pixelarts.masterdetailflowexample.ui.detailscreen.DetailActivity
import com.pixelarts.masterdetailflowexample.ui.homescreen.HomeActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun newFragmentComponent(fragmentModule: FragmentModule):FragmentComponent
    fun injectHomeScreen(homeActivity: HomeActivity)
    fun injectDetailScreen(detailActivity: DetailActivity)
}