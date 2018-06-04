package com.wangj.kotlin.app.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * 当前类注释：</p>
 * Author: LeonWang </p>
 * Created: 2018/5/8 - 11:27 </p>
 * Desc: </P>
 * Copyright (C)  2018 lijiawangjun@gmail.com
 */

/**
 * 时间转换
 */
fun getTime(s: String?): String? {
    if (s.isNullOrEmpty()) {
        return s
    }
    //"yyyy-MM-dd'T'HH:mm:ss.SSSXXX"
    val oldDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.getDefault())
    val newDate = SimpleDateFormat("MMM d, yyyy", Locale.getDefault())
    try {
        val data = oldDate.parse(s)
        return newDate.format(data)
    } catch (e: ParseException) {
        return s
    }
}