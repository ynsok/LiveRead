package com.example.arkadiuszkostka.liveread.UI

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.arkadiuszkostka.liveread.Extention.logInfo
import com.example.arkadiuszkostka.liveread.R
import com.example.arkadiuszkostka.liveread.UI.Fragments.Main_Activity_One
import com.example.arkadiuszkostka.liveread.UI.Fragments.Main_Activity_Second
import com.example.arkadiuszkostka.liveread.ViewModel.MainActivityViewModel
import com.example.arkadiuszkostka.liveread.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val FIRST = "First"
    private val TAG = "fragmetnStage"
    private var searchView: android.support.v7.widget.SearchView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mBinding: ActivityMainBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(mBinding.toolbarMainActivity)

        MainActivityViewModel(application)

        if (savedInstanceState != null) {
            supportFragmentManager.beginTransaction().replace(mBinding.MainActivityInclude.id, supportFragmentManager.findFragmentByTag(TAG)).commit()
        } else {
            supportFragmentManager.beginTransaction().replace(mBinding.MainActivityInclude.id, Main_Activity_One.newInstance(FIRST), TAG).commit()
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_view, menu)

        val menuItem: MenuItem? = menu?.findItem(R.id.action_search)
        searchView = menuItem?.actionView as android.support.v7.widget.SearchView
        searchView?.isSubmitButtonEnabled != false

        searchView?.setOnQueryTextListener(object : android.support.v7.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                logInfo(p0.toString(), this)
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                logInfo(p0.toString(), this)
                return true
            }

        })
        menuItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                supportFragmentManager.beginTransaction().replace(Main_Activity_Include.id, Main_Activity_One.newInstance(Main_Activity_One.oneIDN)).commit()
                return true

            }

        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val itemPosition = item?.itemId
        when (itemPosition) {
            R.id.action_search -> supportFragmentManager.beginTransaction().replace(R.id.Main_Activity_Include, Main_Activity_Second.newInstance(Main_Activity_Second.secondID)).commit()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState!!.putString(TAG, TAG)
    }


    companion object {
        const val POSITION = "Position"
        const val NAME_OF_CATEGORY = "Name Of Category"
    }
}
