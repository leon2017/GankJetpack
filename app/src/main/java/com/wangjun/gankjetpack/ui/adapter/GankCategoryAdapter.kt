package com.wangjun.gankjetpack.ui.adapter

import android.arch.paging.PagedListAdapter
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wangj.kotlin.app.utils.getTime
import com.wangj.kotlin.app.utils.random
import com.wangjun.gankjetpack.R
import com.wangjun.gankjetpack.entity.GankResultsItem
import kotlinx.android.synthetic.main.item_category.view.*

/**
 * 当前类注释:  GankCategoryAdapter
 * <p>
 * Author : leon <p>
 * Created: 2018/6/4.下午3:52 <P>
 * Description:
 * <p>
 * E-mail:lijiawangjun@gmail.com
 */
class GankCategoryAdapter : PagedListAdapter<GankResultsItem, GankCategoryAdapter.GankViewHolder>(GankDiffCallback()) {

    var itemClick: (View, GankResultsItem) -> Unit = { _, _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GankViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return GankViewHolder(view)
    }

    override fun onBindViewHolder(holder: GankViewHolder, position: Int) {
        val entity: GankResultsItem? = getItem(position)
        if (entity != null) {
            holder.bindView(position, entity, itemClick)
        }
    }


    inner class GankViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(position: Int, item: GankResultsItem, itemClick: (View, GankResultsItem) -> Unit) {
            val lastPosition = itemCount
            with(item) {
                with(itemView) {
                    var iconText = "无"
                    if (!TextUtils.isEmpty(who)) {
                        iconText = who!!.trim().substring(0, 1).toUpperCase()
                    }
                    tvIcon.text = iconText
                    tvIcon.setBackColor(randomColor())
                    val name = if (TextUtils.isEmpty(who)) "无名氏" else who
                    tvNameDate.text = "$name  ${getTime(createdAt)}"
                    tvSummary.text = desc
                    container.setOnClickListener {
                        itemClick(itemView, item)
                    }
//                    Log.d("lastPosition", lastPosition.toString())
//                    Log.d("position", position.toString())
                    if (lastPosition == position + 1) {
                        divider.visibility = View.GONE
                    } else {
                        divider.visibility = View.VISIBLE
                    }
                    when (position) {
                        0 -> {
                            container.setBackgroundResource(R.drawable.shape_first_normal)
                        }
                        lastPosition - 1 -> {
                            container.setBackgroundResource(R.drawable.shape_last_normal)
                        }
                        else -> {
                            container.setBackgroundColor(Color.WHITE)
                        }
                    }
                }
            }
        }

        private fun randomColor(): Int {
            val red = (0..256).random()
            val green = (0..256).random()
            val blue = (0..256).random()
            return if (red == 0 && green == 0 && blue == 0) {
                randomColor()
            } else {
                Color.rgb(red, green, blue)
            }
        }
    }
}