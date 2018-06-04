package com.wangjun.gankjetpack.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * 当前类注释:  BaseFragment
 * <p>
 * Author : leon <p>
 * Created: 2018/6/1.上午10:10 <P>
 * Description:
 * <p>
 * E-mail:lijiawangjun@gmail.com
 */
abstract class BaseFragment : Fragment() {

    protected var mVisiable = true
    protected var mPrepared = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(layoutRedId(), container, false) as View
        findViews(view)
        initViews()
        mPrepared = true
        lazyLoad()
        initComplete()
        return view
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            mVisiable = true
            onVisiable()
        } else {
            mVisiable = false
            onInVisiable()
        }
    }

    @LayoutRes
    abstract fun layoutRedId(): Int

    abstract fun findViews(view: View)

    abstract fun initViews()

    /**
     * 初始化完成
     * <p>
     * 非懒加载页面初始化完成
     */
    abstract fun initComplete()

    /**
     * 不可见
     */
    protected fun onInVisiable() {

    }

    /**
     * 可见
     */
    protected fun onVisiable() {
        lazyLoad()
    }

    /**
     * 懒加载
     */
    private fun lazyLoad() {
        if (!mPrepared || !mVisiable) {
            return
        }
        lazyData()
    }

    /**
     * 懒加载页面初始化完成
     */
    abstract fun lazyData()

}