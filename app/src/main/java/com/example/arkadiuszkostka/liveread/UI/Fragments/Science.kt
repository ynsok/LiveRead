package com.example.arkadiuszkostka.liveread.UI.Fragments


import android.arch.lifecycle.Observer
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.arkadiuszkostka.liveread.Extention.initialScienceViewModel

import com.example.arkadiuszkostka.liveread.R
import com.example.arkadiuszkostka.liveread.UI.DetailActivity
import com.example.arkadiuszkostka.liveread.UI.MainActivity
import com.example.arkadiuszkostka.liveread.UI.OnItemPosition
import com.example.arkadiuszkostka.liveread.UI.RecyclerAdapter.ScienceAdapter
import com.example.arkadiuszkostka.liveread.databinding.FragmentScienceBinding


class Science : Fragment(),OnItemPosition {

    private val mAdapter =  ScienceAdapter(this)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initialScienceViewModel(this).observe(this, Observer {
            it?.let { it1 -> mAdapter.data = it }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val dataBinding: FragmentScienceBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_science,container,false)

        dataBinding.recyclerViewScienceFragment.layoutManager = LinearLayoutManager(context)
        dataBinding.recyclerViewScienceFragment.adapter = mAdapter
        return dataBinding.root
    }

    override fun getItemPosition(position: Int) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(MainActivity.POSITION, position)
        intent.putExtra(MainActivity.NAME_OF_CATEGORY, Science.CATEGORY)
        startActivity(intent)
    }


    companion object {
        val CATEGORY = Science::class.java.simpleName!!

    }

}
