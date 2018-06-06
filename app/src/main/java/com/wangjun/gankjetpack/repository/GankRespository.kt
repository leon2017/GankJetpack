package com.wangjun.gankjetpack.repository

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import android.arch.paging.RxPagedListBuilder
import com.wangjun.gankjetpack.base.BaseRepository
import com.wangjun.gankjetpack.engine.NetworkState
import com.wangjun.gankjetpack.entity.GankResultsItem
import com.wangjun.gankjetpack.repository.GankListDataSource.Companion.DEF_PAGE_SIZE
import io.reactivex.Observable
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
class GankRespository constructor(var gankFctory: GankListDataSourceFactory) : BaseRepository() {

    fun fetchGankData(): Observable<PagedList<GankResultsItem>> {

        val config = PagedList.Config.Builder()
                .setInitialLoadSizeHint(DEF_PAGE_SIZE)
                .setPageSize(DEF_PAGE_SIZE)
                .build()

        val gankObservable: Observable<PagedList<GankResultsItem>> = RxPagedListBuilder(gankFctory, config)
                .setInitialLoadKey(1)
                .setFetchScheduler(Schedulers.io())
                .setNotifyScheduler(AndroidSchedulers.mainThread())
                .buildObservable()
        return gankObservable
    }

    fun getLoadDataStatus(): Observable<NetworkState> {
        return gankFctory.observableEmitter.switchMap { dataSource ->
            dataSource.mLoadStatus
        }
    }

    @SuppressLint("CheckResult")
    fun refreshGankData(): LiveData<GankListDataSource> {
        return gankFctory.postLiveData!!
    }
}