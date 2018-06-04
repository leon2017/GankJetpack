package com.wangjun.gankjetpack.engine

import com.wangjun.gankjetpack.entity.GankEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * 当前类注释: Gank API
 * <p>
 * Author : leon <p>
 * Created: 2018/6/1.上午10:31 <P>
 * Description:
 * <p>
 * E-mail:lijiawangjun@gmail.com
 */
interface GankService {

    @GET("data/{type}/{pageSize}/{page}")
    fun gank(@Path("type") type: String,
             @Path("page") page: Int,
             @Path("pageSize") pageSize: Int): Observable<GankEntity>
}