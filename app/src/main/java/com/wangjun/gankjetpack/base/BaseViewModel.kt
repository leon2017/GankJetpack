package com.wangjun.gankjetpack.base

import android.arch.lifecycle.ViewModel

/**
 * 当前类注释:  BaseViewModel
 * <p>
 * Author : leon <p>
 * Created: 2018/5/31.下午6:35 <P>
 * Description:
 * <p>
 * E-mail:lijiawangjun@gmail.com
 */
open class BaseViewModel(var repository: BaseRepository) : ViewModel() {

    override fun onCleared() {
        super.onCleared()
        this.repository.destory()
    }
}