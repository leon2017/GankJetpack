package com.wangjun.gankjetpack.repository

import android.arch.paging.PageKeyedDataSource
import com.wangjun.gankjetpack.engine.ApiCreate
import com.wangjun.gankjetpack.engine.GankService
import com.wangjun.gankjetpack.engine.NetworkState
import com.wangjun.gankjetpack.entity.GankResultsItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

/**
 * 当前类注释:
 * <p>
 * Author : leon <p>
 * Created: 2018/6/1.下午4:37 <P>
 * Description:
 * <p>
 * E-
 * mail:lijiawangjun@gmail.com
 */
class GankListDataSource constructor(val category: String) : PageKeyedDataSource<Int,GankResultsItem>(){

    companion object {
       const val DEF_PAGE_SIZE = 20
    }

    var mLoadStatus : PublishSubject<NetworkState> = PublishSubject.create()

    var mDisposables: CompositeDisposable = CompositeDisposable()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, GankResultsItem>) {
        mLoadStatus.onNext(NetworkState.LOADING)
        val disposable=  ApiCreate.instance
                .create(GankService::class.java)
                .gank(category, 1, params.requestedLoadSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    callback.onResult(it.results!!,1,2)
                    mLoadStatus.onNext(NetworkState.LOADED)
                },{
                    mLoadStatus.onNext(NetworkState.error("网络加载失败"))
                })
        mDisposables.add(disposable)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, GankResultsItem>) {
        mLoadStatus.onNext(NetworkState.LOADED)
    }


    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, GankResultsItem>) {
        mLoadStatus.onNext(NetworkState.LOADING)
        val disposable = ApiCreate.instance
                .create(GankService::class.java)
                .gank(category,params.key,params.requestedLoadSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    callback.onResult(it.results!!,params.key+1)
                    mLoadStatus.onNext(NetworkState.LOADED)
                },{
                    mLoadStatus.onNext(NetworkState.error("网络加载失败"))
                })
        mDisposables.add(disposable)
    }


    override fun invalidate() {
        super.invalidate()
        mDisposables.dispose()
    }

}