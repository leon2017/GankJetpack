package com.wangjun.gankjetpack.base

import android.graphics.Bitmap
import android.os.Build
import android.support.v7.widget.Toolbar
import android.view.KeyEvent
import android.view.View
import android.webkit.*
import android.widget.ProgressBar
import com.wangjun.gankjetpack.R


/**
 * 当前类注释：base webview </p>
 * Author: LeonWang </p>
 * Created: 2018/5/8 - 12:00 </p>
 * Desc: </P>
 * Copyright (C)  2018 lijiawangjun@gmail.com
 */
abstract class BaseBrowerActivity : BaseActivity() {

    protected lateinit var mToolbar: Toolbar
    protected lateinit var mWebView: WebView
    protected lateinit var mProgress: ProgressBar

    override fun layoutResId(): Int {
        return R.layout.activity_base_brower
    }

    override fun findViews() {
        mToolbar = findViewById(R.id.toolbar)
        mWebView = findViewById(R.id.webview)
        mProgress = findViewById(R.id.loading_progress)
    }

    override fun initViews() {
        initToolBar()
        initWebSetting()
        initWebClient()
        initWebChromeClient()
    }

    private fun initToolBar() {
        mToolbar.title = getString(R.string.text_loading)
        mToolbar.setTitleTextColor(resources.getColor(R.color.colorPrimary))
        mToolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun initWebSetting() {
        val settings = mWebView.settings
        //支持获取手势焦点
        mWebView.requestFocusFromTouch()
        //支持JS
        settings.javaScriptEnabled = true
        //支持插件
        settings.pluginState = WebSettings.PluginState.ON
        //设置适应屏幕
        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true
        //支持缩放
        settings.setSupportZoom(false)
        //隐藏原生的缩放控件
        settings.displayZoomControls = false
        //支持内容重新布局
        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        settings.supportMultipleWindows()
        settings.setSupportMultipleWindows(true)
        //设置缓存模式
        settings.domStorageEnabled = true
        settings.databaseEnabled = true
        settings.cacheMode = WebSettings.LOAD_DEFAULT
        settings.setAppCacheEnabled(true)
        settings.setAppCachePath(mWebView.context.cacheDir.absolutePath)
        //设置可访问文件
        settings.allowFileAccess = true
        //当webview调用requestFocus时为webview设置节点
        settings.setNeedInitialFocus(true)
        //支持自动加载图片
        settings.loadsImagesAutomatically = Build.VERSION.SDK_INT >= 19
        settings.setNeedInitialFocus(true)
        //设置编码格式
        settings.defaultTextEncodingName = "UTF-8"
    }

    private fun initWebClient() {
        mWebView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                mProgress.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                mProgress.visibility = View.GONE
            }

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                view!!.loadUrl(request.toString())
                return true
            }
        }
    }

    private fun initWebChromeClient() {
        mWebView.webChromeClient = object : WebChromeClient() {

            override fun onReceivedTitle(view: WebView?, title: String?) {
                super.onReceivedTitle(view, title)
                if (!title.isNullOrEmpty()) {
                    if (title!!.contains(".jpg") ||
                            title.contains(".jpeg") ||
                            title.contains(".png") ||
                            title.contains(".gif")) {
                        mToolbar.title = "妹纸"
                    } else {
                        mToolbar.title = title
                    }
                }
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}