package com.wangj.kotlin.app.utils

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.preference.PreferenceManager
import android.view.View
import androidx.core.content.edit
import com.wangjun.gankjetpack.R

/**
 * 当前类注释：</p>
 * Author: LeonWang </p>
 * Created: 2018/5/8 - 14:53 </p>
 * Desc: </P>
 * Copyright (C)  2018 lijiawangjun@gmail.com
 */
class ThemeUtil(val activity: Activity) {

    companion object {
        val STYLE_NAME = "style_theme_name"
        fun init(activity: Activity) = ThemeUtil(activity)
    }

    fun initTheme(): Boolean {
        val styleName = getStyleName()
        if (styleName) {
            activity.setTheme(R.style.NightTheme)
            activity.window.decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                activity.window.navigationBarColor = activity.resources.getColor(R.color.colorPrimary)
                activity.window.statusBarColor = activity.resources.getColor(R.color.colorPrimary)
            }
        } else {
            activity.setTheme(R.style.DayTheme)
            activity.window.decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                activity.window.navigationBarColor = Color.GRAY
                activity.window.statusBarColor = Color.WHITE
            }
        }
        return styleName
    }

    fun changeTheme() {
        val styleName = initTheme()
        saveStyleName(!styleName)
        activity.recreate()
    }

    private fun saveStyleName(theme: Boolean) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(activity)
//        preferences.edit().putBoolean(STYLE_NAME, theme).apply(
        preferences.edit{
            putBoolean(STYLE_NAME,theme)
        }
    }

    private fun getStyleName(): Boolean {
        val preferences = PreferenceManager.getDefaultSharedPreferences(activity)
        return preferences.getBoolean(STYLE_NAME, true)
    }
}