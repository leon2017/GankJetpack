package com.wangjun.gankjetpack.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.wangj.kotlin.app.utils.ThemeUtil

/**
 * 当前类注释:
 * <p>
 * Author : leon <p>
 * Created: 2018/6/1.上午10:09 <P>
 * Description:
 * <p>
 * E-mail:lijiawangjun@gmail.com
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeUtil.init(this).initTheme()
        setContentView(layoutResId())
        findViews()
        initViews()
        initDatas()
    }

    /**
     * 布局 id
     */
    @LayoutRes
    abstract fun layoutResId(): Int

    abstract fun findViews()

    abstract fun initViews()

    abstract fun initDatas()

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
            else -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }
}