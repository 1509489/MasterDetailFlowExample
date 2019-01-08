package com.pixelarts.masterdetailflowexample.ui.homescreen

import com.pixelarts.masterdetailflowexample.base.BasePresenter
import com.pixelarts.masterdetailflowexample.base.BaseView
import com.pixelarts.masterdetailflowexample.model.Collection

interface HomeContract {
    interface View: BaseView{
        fun showArticles(collection: List<Collection>)
    }

    interface Presenter: BasePresenter{
        fun getArticles()
    }
}