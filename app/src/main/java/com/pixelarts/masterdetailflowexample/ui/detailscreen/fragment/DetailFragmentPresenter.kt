package com.pixelarts.masterdetailflowexample.ui.detailscreen.fragment

import com.pixelarts.masterdetailflowexample.model.Collection

class DetailFragmentPresenter(private val view: DetailFragmentContract.View): DetailFragmentContract.Presenter {

    override fun getDetails(collection: Collection) {
        view.showTitle(collection)
    }

    override fun onResume() {

    }

    override fun onStop() {

    }
}