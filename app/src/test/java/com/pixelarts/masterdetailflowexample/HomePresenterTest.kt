package com.pixelarts.masterdetailflowexample

import com.pixelarts.masterdetailflowexample.model.Response
import com.pixelarts.masterdetailflowexample.net.NetworkService
import com.pixelarts.masterdetailflowexample.ui.homescreen.HomeContract
import com.pixelarts.masterdetailflowexample.ui.homescreen.HomePresenter
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.*
import java.util.concurrent.Executor

@RunWith(MockitoJUnitRunner::class)
class HomePresenterTest {
    private lateinit var presenter: HomePresenter
    private lateinit var netResponse: Response

    @Mock private lateinit var view: HomeContract.View
    @Mock private lateinit var netService: NetworkService

    companion object {
        @BeforeClass
        @JvmStatic
        fun setupSchedulers(){
            val scheduler = object : Scheduler(){
                override fun createWorker(): Worker {
                    return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
                }
            }
            RxJavaPlugins.setInitIoSchedulerHandler { scheduler }
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler }
        }
    }

    @Before
    fun setup(){
        presenter = HomePresenter(view, netService)
        netResponse = Response(Collections.emptyList())
    }

    @Test
    fun testApiSuccess(){
        Mockito.`when`(netService.getArticles()).thenReturn(Single.just(netResponse))
        presenter.getArticles()
        Mockito.verify(view).showArticles(Mockito.anyList())
    }

    @Test
    fun testApiFailure(){
        Mockito.`when`(netService.getArticles()).thenReturn(Single.error(Throwable()))
        presenter.getArticles()
        Mockito.verify(view).showError("Error: ${Throwable().message}")
    }
}