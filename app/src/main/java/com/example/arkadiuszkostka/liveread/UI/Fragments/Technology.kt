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
import com.example.arkadiuszkostka.liveread.Extention.initialTechnologyViewModel

import com.example.arkadiuszkostka.liveread.R
import com.example.arkadiuszkostka.liveread.UI.DetailActivity
import com.example.arkadiuszkostka.liveread.UI.MainActivity
import com.example.arkadiuszkostka.liveread.UI.OnItemPosition
import com.example.arkadiuszkostka.liveread.UI.RecyclerAdapter.TechnologyAdapter
import com.example.arkadiuszkostka.liveread.databinding.FragmentTechnologyBinding

class Technology : Fragment(),OnItemPosition {

    private val mAdapter =  TechnologyAdapter(this)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initialTechnologyViewModel(this).observe(this, Observer {
            it?.let { it1 -> mAdapter.data = it}
        })
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val dataBindingUtil: FragmentTechnologyBinding =
                DataBindingUtil.inflate(inflater,R.layout.fragment_technology
                ,container,false)

        dataBindingUtil.recyclerViewTechnologyFragmentRecycler.layoutManager = LinearLayoutManager(context)
        dataBindingUtil.recyclerViewTechnologyFragmentRecycler.adapter = mAdapter


        return dataBindingUtil.root
    }

    override fun getItemPosition(position: Int) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(MainActivity.POSITION, position)
        intent.putExtra(MainActivity.NAME_OF_CATEGORY, Technology.CATEGORY)
        startActivity(intent)
    }


    companion object {
        val CATEGORY = Technology::class.java.simpleName!!

    }



}
