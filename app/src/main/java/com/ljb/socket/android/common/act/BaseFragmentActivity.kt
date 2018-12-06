package com.ljb.socket.android.common.act

import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import mvp.ljb.kt.contract.IPresenterContract
import mvp.ljb.kt.view.MvpFragmentActivity

abstract class BaseFragmentActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        init(savedInstanceState)
        initView()
        initData()
    }

    protected abstract fun getLayoutId(): Int

    protected open fun init(savedInstanceState: Bundle?) {}

    protected open fun initView() {}

    protected open fun initData() {}

    override fun getResources(): Resources {
        val res = super.getResources()
        if (res.configuration.fontScale != 1.0f) {
            val newConfig = Configuration()
            newConfig.setToDefaults()
            res.updateConfiguration(newConfig, res.displayMetrics)
        }
        return res
    }


}