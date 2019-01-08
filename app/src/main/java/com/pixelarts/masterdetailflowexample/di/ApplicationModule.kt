package com.pixelarts.masterdetailflowexample.di

import com.pixelarts.masterdetailflowexample.base.BaseActivity
import com.pixelarts.masterdetailflowexample.net.NetworkService
import com.pixelarts.masterdetailflowexample.ui.homescreen.HomeActivity
import com.pixelarts.masterdetailflowexample.ui.homescreen.HomeContract
import com.pixelarts.masterdetailflowexample.ui.homescreen.HomePresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class ApplicationModule(private val baseActivity: BaseActivity<*>) {

    @Provides
    @Singleton
    fun providesHomePresenter(networkService: NetworkService): HomeContract.Presenter = HomePresenter( baseActivity as HomeActivity, networkService )
}