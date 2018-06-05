package com.wangjun.gankjetpack.engine

/**
 * 当前类注释:
 * <p>
 * Author : leon <p>
 * Created: 2018/6/4.上午11:51 <P>
 * Description:
 * <p>
 * E-mail:lijiawangjun@gmail.com
 */

@Suppress("DataClassPrivateConstructor")
data class NetworkState private constructor( val status: Status,
                                             val msg: String? = null) {
    companion object {
        val LOADED = NetworkState(Status.SUCCESS)
        val LOADING = NetworkState(Status.RUNNING)
        fun error(msg: String?) = NetworkState(Status.FAILED, msg)
    }
}

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED
}