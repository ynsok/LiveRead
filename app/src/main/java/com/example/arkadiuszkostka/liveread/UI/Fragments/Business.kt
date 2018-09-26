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
import com.example.arkadiuszkostka.liveread.Extention.initialBusinessViewModel
import com.example.arkadiuszkostka.liveread.R
import com.example.arkadiuszkostka.liveread.UI.DetailActivity
import com.example.arkadiuszkostka.liveread.UI.MainActivity
import com.example.arkadiuszkostka.liveread.UI.OnItemPosition
import com.example.arkadiuszkostka.liveread.UI.RecyclerAdapter.BuissnessAdapter
import com.example.arkadiuszkostka.liveread.databinding.FragmentBusinessBinding


class Business : Fragment(), OnItemPosition {


    private val buissnessAdapter: BuissnessAdapter = BuissnessAdapter(this)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initialBusinessViewModel(this).observe(this, Observer {
            it?.let { it1 -> buissnessAdapter.data = it }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        val binding: FragmentBusinessBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_business, container, false)

        binding.recyclerViewBusinessFragment.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewBusinessFragment.adapter = buissnessAdapter

        return binding.root

    }

    override fun getItemPosition(position: Int) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(MainActivity.POSITION, position)
        intent.putExtra(MainActivity.NAME_OF_CATEGORY, CATEGORY)
        startActivity(intent)

    }

    companion object {
        val CATEGORY = Business::class.java.simpleName!!

    }


}
