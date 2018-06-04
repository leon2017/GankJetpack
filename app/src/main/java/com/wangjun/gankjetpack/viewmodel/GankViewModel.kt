package com.wangjun.gankjetpack.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import com.wangjun.gankjetpack.base.BaseViewModel
import com.wangjun.gankjetpack.entity.GankResultsItem
import com.wangjun.gankjetpack.repository.GankRespository


/**
 * 当前类注释:
 * <p>
 * Author : leon <p>
 * Created: 2018/6/1.上午10:32 <P>
 * Description:
 * <p>
 * E-mail:lijiawangjun@gmail.com
 */
class GankViewModel(var gankRespository: GankRespository) : BaseViewModel(gankRespository) {

    private lateinit var mData: LiveData<PagedList<GankResultsItem?>>

    private lateinit var mType: String
    private var mPage: Int = 1
    private val PAGE_SIZE = 20


    fun setTypeAndPage(type: String = "all", page: Int = 1) {
        this.mType = type
        this.mPage = page
    }

    fun startRequestGankData() {
        val tempData = gankRespository.fetchGankData(mPage, mType, PAGE_SIZE)


    }

    fun getGankData(): LiveData<PagedList<GankResultsItem?>> {
        return mData
    }

}