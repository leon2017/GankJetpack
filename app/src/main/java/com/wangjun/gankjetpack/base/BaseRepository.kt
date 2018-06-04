package com.wangjun.gankjetpack.base

import io.reactivex.disposables.Disposable

/**
 * 当前类注释: BaseRepository
 * <p>
 * Author : leon <p>
 * Created: 2018/5/31.下午6:36 <P>
 * Description:
 * <p>
 * E-mail:lijiawangjun@gmail.com
 */
open class BaseRepository {

    internal lateinit var mDisposable: Disposable

    fun destory(): Unit {
        if (!mDisposable.isDisposed)
            mDisposable.dispose()
    }
}