package com.wangjun.gankjetpack.entity

/**
 * 当前类注释:
 * <p>
 * Author : leon <p>
 * Created: 2018/6/1.上午10:29 <P>
 * Description:
 * <p>
 * E-mail:lijiawangjun@gmail.com
 */
data class GankEntity(
        val error: Boolean? = null,
        val results: List<GankResultsItem?>? = null
)

data class GankResultsItem(
        val createdAt: String? = null,
        val publishedAt: String? = null,
        val id: String? = null,
        val source: String? = null,
        val used: Boolean? = null,
        val type: String? = null,
        val url: String? = null,
        val desc: String? = null,
        val who: String? = null,
        val images: List<String?>? = null
)