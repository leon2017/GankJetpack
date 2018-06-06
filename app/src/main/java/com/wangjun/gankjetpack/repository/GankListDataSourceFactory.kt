package com.wangjun.gankjetpack.repository

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.wangjun.gankjetpack.entity.GankResultsItem
import io.reactivex.subjects.PublishSubject




/**
 * 当前类注释:
 * <p>
 * Author : leon <p>
 * Created: 2018/6/4.下午1:49 <P>
 * Description:
 * <p>
 * E-mail:lijiawangjun@gmail.com
 */
class GankListDataSourceFactory constructor(val category: String ):  DataSource.Factory<Int,GankResultsItem>(){

    var observableEmitter : PublishSubject<GankListDataSource> = PublishSubject.create()
    var postLiveData: MutableLiveData<GankListDataSource>? = null

    override fun create(): DataSource<Int, GankResultsItem> {
        val dataSource = GankListDataSource(category)
        observableEmitter.onNext(dataSource)
        postLiveData = MutableLiveData()
        postLiveData!!.postValue(dataSource)
        return dataSource
    }
}