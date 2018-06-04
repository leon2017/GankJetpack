package com.wangjun.gankjetpack.ui.adapter

import android.support.v7.util.DiffUtil
import com.wangjun.gankjetpack.entity.GankResultsItem

/**
 * 当前类注释:
 * <p>
 * Author : leon <p>
 * Created: 2018/6/4.下午3:44 <P>
 * Description:
 * <p>
 * E-mail:lijiawangjun@gmail.com
 */
class GankDiffCallback : DiffUtil.ItemCallback<GankResultsItem>() {


    override fun areItemsTheSame(oldItem: GankResultsItem?, newItem: GankResultsItem?): Boolean {
        return oldItem!!.id == newItem!!.id
    }

    override fun areContentsTheSame(oldItem: GankResultsItem?, newItem: GankResultsItem?): Boolean {
        return oldItem == newItem
    }
}