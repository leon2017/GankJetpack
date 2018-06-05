package com.wangjun.gankjetpack

import android.annotation.SuppressLint
import androidx.navigation.Navigation
import com.wangjun.gankjetpack.base.BaseActivity

class MainActivity : BaseActivity() {



    override fun layoutResId(): Int {
        return R.layout.activity_main
    }

    override fun findViews() {

    }

    @SuppressLint("RestrictedApi")
    override fun initViews() {

    }

    override fun initDatas() {

    }

    override fun onSupportNavigateUp()
            = Navigation.findNavController(this, R.id.my_nav_host_fragment).navigateUp()


}
