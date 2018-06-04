package com.wangjun.gankjetpack.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.paging.PagedList
import com.wangjun.gankjetpack.base.BaseViewModel
import com.wangjun.gankjetpack.engine.NetworkState
import com.wangjun.gankjetpack.entity.GankResultsItem
import com.wangjun.gankjetpack.repository.GankRespository
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * 当前类注释:
 * <p>
 * Author : leon <p>
 * Created: 2018/6/1.上午10:32 <P>
 * Description:
 * <p>
 * E-mail:lijiawangjun@gmail.com
 */
class GankViewModel constructor(private var gankRespository: GankRespository) : BaseViewModel(gankRespository) {


    /**
     * 获取gank list data
     */
    fun fetchGankData(): LiveData<PagedList<GankResultsItem>> {
        val result = gankRespository
                .fetchGankData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toFlowable(BackpressureStrategy.BUFFER)

        return LiveDataReactiveStreams.fromPublisher(result)
    }

    /**
     * 获取加载状态
     */
    fun fetchLoadStatus(): LiveData<NetworkState> {
        val result = gankRespository
                .getLoadDataStatus()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toFlowable(BackpressureStrategy.BUFFER)

        return LiveDataReactiveStreams.fromPublisher(result)
    }

    fun refresh(){
//        fetchGankData()
    }

}