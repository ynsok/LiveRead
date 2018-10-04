package com.example.arkadiuszkostka.liveread.UI

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.arkadiuszkostka.liveread.Extention.GONE
import com.example.arkadiuszkostka.liveread.Extention.Visibility
import com.example.arkadiuszkostka.liveread.Extention.logInfo
import com.example.arkadiuszkostka.liveread.R
import com.example.arkadiuszkostka.liveread.UI.Fragments.Main_Activity_One
import com.example.arkadiuszkostka.liveread.UI.Fragments.Main_Activity_Second
import com.example.arkadiuszkostka.liveread.ViewModel.MainActivityViewModel
import com.example.arkadiuszkostka.liveread.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val STATE_OF_SEARCH_VIEW = "stateOfStateView"
    private var CURRENT_STATE: Boolean? = null
    private var menuItem: MenuItem? = null
    private var searchView: android.support.v7.widget.SearchView? = null
    private val QUERY: String = "SEARCH_QUERY_TAG"
    private var mSearchQuery: String = ""
    private var mMainActivityViewModel: MainActivityViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mBinding: ActivityMainBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(mBinding.toolbarMainActivity)
        mMainActivityViewModel = MainActivityViewModel(application)
        if (savedInstanceState != null) {
            CURRENT_STATE = savedInstanceState.getBoolean(STATE_OF_SEARCH_VIEW)
            mSearchQuery = savedInstanceState.getString(QUERY)
            logInfo(mSearchQuery, this)
        } else {
            supportFragmentManager
                    .beginTransaction()
                    .replace(Main_Activity_Include_One.id, Main_Activity_One.newInstance(Main_Activity_One.oneIDN), Main_Activity_One.oneIDN)
                    .commit()
            supportFragmentManager
                    .beginTransaction()
                    .replace(Main_Activity_Include_Second.id, Main_Activity_Second.newInstance(Main_Activity_Second.secondID), Main_Activity_Second.secondID)
                    .commit()
        }
        logInfo("onCreate", this)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_view, menu)

        menuItem = menu?.findItem(R.id.action_search)
        searchView = menuItem?.actionView as android.support.v7.widget.SearchView
        searchView?.setOnQueryTextListener(object : android.support.v7.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                logInfo(p0.toString(), this)
                if (!mSearchQuery.isEmpty()) {
                    mMainActivityViewModel?.startFetchingDataByQuery(mSearchQuery)
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                logInfo(p0.toString(), this)
                mSearchQuery = p0.toString()
                if (!mSearchQuery.isEmpty()) {
                    mMainActivityViewModel?.startFetchingDataByQuery(mSearchQuery)
                }

                return false
            }

        })
        menuItem!!.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                Visibility(Main_Activity_Include_One)
                mMainActivityViewModel?.delateAllKeywordData()
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {

        if (searchView != null && menuItem != null) {
            val query = mSearchQuery
            if (CURRENT_STATE != null && !CURRENT_STATE!!) {
                Visibility(Main_Activity_Include_Second)
                GONE(Main_Activity_Include_One)
                menuItem!!.expandActionView()
                searchView!!.setQuery(query, true)
                searchView!!.clearFocus()
            }
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val itemPosition = item?.itemId
        when (itemPosition) {
            R.id.action_search -> {
                GONE(Main_Activity_Include_One)
                Visibility(Main_Activity_Include_Second)
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onSaveInstanceState(outState: Bundle?) {

        outState?.putString(QUERY, mSearchQuery)
        logInfo(mSearchQuery, this)
        val checkTheStateException = searchView?.isIconified
        checkTheStateException?.let { outState?.putBoolean(STATE_OF_SEARCH_VIEW, it) }
        super.onSaveInstanceState(outState)
    }

    companion object {
        const val POSITION = "Position"
        const val NAME_OF_CATEGORY = "Name Of Category"
    }
}
