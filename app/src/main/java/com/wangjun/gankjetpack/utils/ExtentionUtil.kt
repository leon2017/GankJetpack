package com.wangj.kotlin.app.utils

import java.util.*

/**
 * 当前类注释：扩展函数工具</p>
 * Author: LeonWang </p>
 * Created: 2018/5/8 - 10:39 </p>
 * Desc: </P>
 * Copyright (C)  2018 lijiawangjun@gmail.com
 */

/**
 * 随机数生成
 */
fun ClosedRange<Int>.random() =
        Random().nextInt(endInclusive - start) +  start