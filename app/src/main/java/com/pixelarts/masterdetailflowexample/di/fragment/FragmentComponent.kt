package com.pixelarts.masterdetailflowexample.di.fragment

import com.pixelarts.masterdetailflowexample.ui.detailscreen.fragment.DetailFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [FragmentModule::class])
interface FragmentComponent {
    fun injectDetailFragment(detailFragment: DetailFragment)
}