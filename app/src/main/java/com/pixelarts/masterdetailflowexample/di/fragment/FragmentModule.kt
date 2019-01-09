package com.pixelarts.masterdetailflowexample.di.fragment

import androidx.fragment.app.Fragment
import com.pixelarts.masterdetailflowexample.ui.detailscreen.fragment.DetailFragment
import com.pixelarts.masterdetailflowexample.ui.detailscreen.fragment.DetailFragmentContract
import com.pixelarts.masterdetailflowexample.ui.detailscreen.fragment.DetailFragmentPresenter
import dagger.Module
import dagger.Provides

@Module
class FragmentModule(private val fragment: Fragment) {

    @Provides
    @FragmentScope
    fun providesDetailFragmentPresenter(): DetailFragmentContract.Presenter = DetailFragmentPresenter(fragment as DetailFragment)
}