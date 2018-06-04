package com.wangjun.gankjetpack.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.wangjun.gankjetpack.R
import com.wangjun.gankjetpack.base.BaseFragment

/**
 * 当前类注释:
 * <p>
 * Author : leon <p>
 * Created: 2018/6/1.上午10:37 <P>
 * Description:
 * <p>
 * E-mail:lijiawangjun@gmail.com
 */
class MainFragment: BaseFragment() {

    //福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
    private val indexList = arrayOf("all", "Android", "iOS", "休息视频", "拓展资源", "前端", "福利")
    private var mIndex = "all"

    companion object {
        private val CATEGORY_INDEX = "category_index"
        fun newInstance(index: Int): Fragment {
            val fragment = MainFragment()
            var bundle = Bundle()
            bundle.putInt(CATEGORY_INDEX, index)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun layoutRedId(): Int {
        return R.layout.fragment_main
    }

    override fun findViews(view: View) {
    }

    override fun initViews() {
    }

    override fun initComplete() {
    }

    override fun lazyData() {
    }

}