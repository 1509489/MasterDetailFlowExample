package com.pixelarts.masterdetailflowexample.ui.homescreen

import com.pixelarts.masterdetailflowexample.model.Response
import com.pixelarts.masterdetailflowexample.net.NetworkService
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class HomePresenter(private val view: HomeContract.View, private val networkService: NetworkService): HomeContract.Presenter {

    override fun getArticles() {
        networkService.getArticles().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Response>{

                override fun onSuccess(t: Response) {
                    view.showArticles(t.collection)
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {
                    view.showError("Error: ${e.message}")
                }
            })
    }

    override fun onResume() {

    }

    override fun onStop() {

    }
}