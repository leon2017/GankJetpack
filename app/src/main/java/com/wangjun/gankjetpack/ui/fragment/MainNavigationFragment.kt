package com.wangjun.gankjetpack.ui.fragment

import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.navigation.Navigation
import com.wangj.kotlin.app.utils.ThemeUtil
import com.wangjun.gankjetpack.R
import com.wangjun.gankjetpack.base.BaseFragment
import com.wangjun.gankjetpack.ui.adapter.MainPagerAdapter



/**
 * 当前类注释:
 * <p>
 * Author : leon <p>
 * Created: 2018/6/5.上午9:25 <P>
 * Description:
 * <p>
 * E-mail:lijiawangjun@gmail.com
 */
class MainNavigationFragment : BaseFragment() {

    private lateinit var mTabLayout: TabLayout
    private lateinit var mViewPage: ViewPager
    protected lateinit var mToolbar: Toolbar
    private lateinit var mView: View

    override fun layoutRedId(): Int {
        return R.layout.fragment_main_navigation
    }

    override fun findViews(view: View) {
        mView = view
        mTabLayout = view.findViewById(R.id.tabLayout)
        mViewPage = view.findViewById(R.id.view_pager)
        mToolbar = view.findViewById(R.id.toolbar)
        setHasOptionsMenu(true)
    }

    override fun initViews() {
        (activity as AppCompatActivity).setSupportActionBar(mToolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun initComplete() {
        val categoryArray = resources.getStringArray(R.array.GankCategoryArray)
        var fragments = ArrayList<Fragment>()
        for (index in categoryArray.indices) {
            val fragment: MainFragment = MainFragment.newInstance(index)
            fragment.mainItemClick = { bundle ->
                Navigation.findNavController(mView).navigate(
                        R.id.action_main_fragment_to_article_detail_activity,
                        bundle
                )
            }
            fragments.add(fragment)
        }
        var adapter = MainPagerAdapter(childFragmentManager, categoryArray, fragments)
        mViewPage.adapter = adapter
        mTabLayout.setupWithViewPager(mViewPage)
    }

    override fun lazyData() {
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater);
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.menu_theme -> {
                if (activity != null)
                    ThemeUtil.init(activity!!).changeTheme()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}