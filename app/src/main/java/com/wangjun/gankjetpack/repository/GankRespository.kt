package com.wangjun.gankjetpack.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.wangjun.gankjetpack.base.BaseRepository
import com.wangjun.gankjetpack.engine.ApiCreate
import com.wangjun.gankjetpack.engine.GankService
import com.wangjun.gankjetpack.entity.GankEntity
import com.wangjun.gankjetpack.entity.GankResultsItem
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
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
class GankRespository : BaseRepository() {


    fun fetchGankData(page: Int, type: String, pageSize: Int): LiveData<List<GankResultsItem?>> {
        val data = MutableLiveData<List<GankResultsItem?>>()

        ApiCreate.instance
                .create(GankService::class.java)
                .gank(type, page, pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<GankEntity> {
                    override fun onComplete() {
                        Log.d("GankRespository", "onComplete")
                    }

                    override fun onSubscribe(d: Disposable) {
                        Log.d("GankRespository", "onSubscribe")
                        mDisposable = d
                    }

                    override fun onNext(t: GankEntity) {
                        Log.d("GankRespository", "onNext")
                        if (t.results != null && t.results.isNotEmpty())
                            data.value = t.results
                    }

                    override fun onError(e: Throwable) {
                        Log.d("GankRespository", "onError")
                    }

                })

        return data
    }
}