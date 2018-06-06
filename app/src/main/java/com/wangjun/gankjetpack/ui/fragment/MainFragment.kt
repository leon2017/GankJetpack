package com.wangjun.gankjetpack.ui.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import androidx.core.os.bundleOf
import com.wangjun.gankjetpack.R
import com.wangjun.gankjetpack.base.BaseFragment
import com.wangjun.gankjetpack.engine.Status
import com.wangjun.gankjetpack.repository.GankListDataSourceFactory
import com.wangjun.gankjetpack.repository.GankRespository
import com.wangjun.gankjetpack.ui.activity.ARTICLE_URL
import com.wangjun.gankjetpack.ui.adapter.GankCategoryAdapter
import com.wangjun.gankjetpack.viewmodel.GankViewModel

/**
 * 当前类注释:
 * <p>
 * Author : leon <p>
 * Created: 2018/6/1.上午10:37 <P>
 * Description:
 * <p>
 * E-mail:lijiawangjun@gmail.com
 */
class MainFragment : BaseFragment() {

    //福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
    private val indexList = arrayOf("all", "Android", "iOS", "休息视频", "拓展资源", "前端", "福利")
    private var mIndex = "all"
    private lateinit var gankViewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: GankViewModel
    private lateinit var mRefresh: SwipeRefreshLayout
    private lateinit var mList: RecyclerView
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var mAdapter: GankCategoryAdapter


    var mainItemClick: (Bundle) -> Unit = { _ -> }

    companion object {
        private val CATEGORY_INDEX = "category_index"
        fun newInstance(index: Int): MainFragment {
            val fragment = MainFragment()
            var bundle = bundleOf(CATEGORY_INDEX to index)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun layoutRedId(): Int {
        return R.layout.fragment_main
    }

    override fun findViews(view: View) {
        arguments?.let {
            mIndex = indexList[it[CATEGORY_INDEX] as Int]
        }
        mRefresh = view.findViewById(R.id.swipe_refresh_layout)
        mList = view.findViewById(R.id.recyclerView)
    }

    override fun initViews() {
        viewModel = getViewModel(mIndex)
        mLayoutManager = LinearLayoutManager(activity)
        mList.layoutManager = mLayoutManager
        mAdapter = GankCategoryAdapter()
        mAdapter.itemClick = { itemView, gankEntity ->
            val bundle = bundleOf(ARTICLE_URL to gankEntity.url)
            mainItemClick(bundle)
        }
        mList.adapter = mAdapter

        mRefresh.setOnRefreshListener {
            //            Toast.makeText(activity, "刷新", Toast.LENGTH_SHORT).show()
            viewModel.refresh()
        }
    }

    override fun initComplete() {

        viewModel.fetchLoadStatus().observe(this, Observer {
            it?.let {
                when (it.status) {
                    Status.RUNNING -> {
                        mRefresh.isRefreshing = true
                    }
                    Status.SUCCESS -> {
                        mRefresh.isRefreshing = false
                    }
                    Status.FAILED -> {
                        mRefresh.isRefreshing = false
                    }
                }
            }
        })

        viewModel.fetchGankData().observe(this, Observer { result ->
            result?.let {
                mAdapter.submitList(it)
            }
        })
    }

    override fun lazyData() {
    }

    private fun getViewModel(type: String): GankViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val respository = GankRespository(GankListDataSourceFactory(type))
                @Suppress("UNCHECKED_CAST")
                return GankViewModel(respository) as T
            }
        })[GankViewModel::class.java]
    }

}