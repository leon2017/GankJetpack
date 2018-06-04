package com.wangjun.gankjetpack.repository

import android.arch.paging.PositionalDataSource
import com.wangjun.gankjetpack.entity.GankResultsItem

/**
 * 当前类注释:
 * <p>
 * Author : leon <p>
 * Created: 2018/6/1.下午4:37 <P>
 * Description:
 * <p>
 * E-mail:lijiawangjun@gmail.com
 */
class GankListDataSource(list: List<GankResultsItem?>?) : PositionalDataSource<GankResultsItem>() {

    private var mList: List<GankResultsItem> = ArrayList<GankResultsItem>(list)

    override fun loadInitial(params: PositionalDataSource.LoadInitialParams,
                             callback: PositionalDataSource.LoadInitialCallback<GankResultsItem>) {
        val totalCount = mList.size

        val position = PositionalDataSource.computeInitialLoadPosition(params, totalCount)
        val loadSize = PositionalDataSource.computeInitialLoadSize(params, position, totalCount)

        val sublist = mList.subList(position, position + loadSize)
        callback.onResult(sublist, position, totalCount)
    }

    override fun loadRange(params: PositionalDataSource.LoadRangeParams,
                           callback: PositionalDataSource.LoadRangeCallback<GankResultsItem>) {
        callback.onResult(mList.subList(params.startPosition,
                params.startPosition + params.loadSize))
    }
}