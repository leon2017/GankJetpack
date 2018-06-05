package com.wangjun.gankjetpack.ui.activity

import com.wangjun.gankjetpack.base.BaseBrowerActivity


val ARTICLE_URL = "article_url"


/**
 * 文章详情
 */
class ArticleDetailActivity : BaseBrowerActivity() {

    protected lateinit var webviewUrl: String

    override fun initViews() {
        super.initViews()
        intent.extras?.let {
            webviewUrl = it[ARTICLE_URL] as String
        }
    }

    override fun initDatas() {
        mWebView.loadUrl(webviewUrl)
    }
}
