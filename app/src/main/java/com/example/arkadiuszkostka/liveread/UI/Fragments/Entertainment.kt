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
import com.example.arkadiuszkostka.liveread.Extention.initialEntertainmentViewModel
import com.example.arkadiuszkostka.liveread.R
import com.example.arkadiuszkostka.liveread.UI.DetailActivity
import com.example.arkadiuszkostka.liveread.UI.MainActivity
import com.example.arkadiuszkostka.liveread.UI.OnItemPosition
import com.example.arkadiuszkostka.liveread.UI.RecyclerAdapter.EntertainmentAdapter
import com.example.arkadiuszkostka.liveread.databinding.FragmentEntertainmentBinding


class Entertainment : Fragment(), OnItemPosition {

    private val entertainmentAdapter: EntertainmentAdapter = EntertainmentAdapter(this)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        initialEntertainmentViewModel(this).observe(this, Observer {
            it?.let { it1 -> entertainmentAdapter.data = it }
        })

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val bindingUtil: FragmentEntertainmentBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_entertainment,container,false)
        bindingUtil.recyclerViewEntertainmentFragment.layoutManager = LinearLayoutManager(context)
        bindingUtil.recyclerViewEntertainmentFragment.adapter = entertainmentAdapter
        return bindingUtil.root
    }

    override fun getItemPosition(position: Int) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(MainActivity.POSITION, position)
        intent.putExtra(MainActivity.NAME_OF_CATEGORY, Entertainment.CATEGORY)
        startActivity(intent)
    }


    companion object {
        val CATEGORY = Entertainment::class.java.simpleName!!

    }


}
