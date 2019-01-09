package com.pixelarts.masterdetailflowexample.ui.detailscreen.fragment

import com.pixelarts.masterdetailflowexample.base.BasePresenter
import com.pixelarts.masterdetailflowexample.base.BaseView
import com.pixelarts.masterdetailflowexample.model.Collection

interface DetailFragmentContract {
    interface View: BaseView{
        fun showTitle(collection: Collection): String
    }

    interface Presenter: BasePresenter{
        fun getDetails(collection: Collection)
    }
}