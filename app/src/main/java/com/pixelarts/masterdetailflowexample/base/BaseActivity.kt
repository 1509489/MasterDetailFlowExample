package com.pixelarts.masterdetailflowexample.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<Preseter: BasePresenter>: AppCompatActivity(), BaseView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    abstract fun init()
    abstract fun getPresenterView():Preseter
}