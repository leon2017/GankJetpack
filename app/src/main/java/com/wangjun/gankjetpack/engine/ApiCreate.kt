package com.wangjun.gankjetpack.engine

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * 当前类注释:
 * <p>
 * Author : leon <p>
 * Created: 2018/5/31.上午11:12 <P>
 * Description:
 * <p>
 * E-mail:lijiawangjun@gmail.com
 */
class ApiCreate {

    private val DEFAULT_TIMEOUT: Long = 15
    private val BASE_URL = "http://www.gank.io/api/"
    var mOkHttpClient: OkHttpClient? = null
    var mRetrofit: Retrofit? = null

    init {
        val logger = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            Log.d("GankApi", it)
        })
        logger.level = HttpLoggingInterceptor.Level.BASIC
        mOkHttpClient = OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(logger)
                .build()

        mRetrofit = Retrofit.Builder()
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
    }

    companion object {
        @Volatile
        var sApiCreate: ApiCreate? = null

        private fun initApiCreate(): ApiCreate {
            if (sApiCreate == null) {
                synchronized(ApiCreate::class.java) {
                    if (sApiCreate == null) {
                        sApiCreate = ApiCreate()
                    }
                }
            }
            return sApiCreate!!
        }

        val instance: Retrofit
            get() = initApiCreate().mRetrofit!!
    }
}