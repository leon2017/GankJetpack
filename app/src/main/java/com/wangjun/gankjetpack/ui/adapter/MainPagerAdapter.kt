package com.wangjun.gankjetpack.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * 当前类注释：</p>
 * Author: LeonWang </p>
 * Created: 2018/5/4 - 11:32 </p>
 * Desc: </P>
 * Copyright (C)  2018 lijiawangjun@gmail.com
 */
class MainPagerAdapter(fm: FragmentManager,
                       val titles: Array<String>,
                       val framents: List<Fragment>) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        return if (framents.isNotEmpty()) framents[position] else null
    }

    override fun getCount(): Int {
        return framents.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}