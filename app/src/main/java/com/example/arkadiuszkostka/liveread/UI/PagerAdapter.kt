package com.example.arkadiuszkostka.liveread.UI

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.arkadiuszkostka.liveread.UI.Fragments.*

class PagerAdapter(fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager) {
    private val NUM_OF_FRAGMENT:Int = 6

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> Business()
            1 -> Entertainment()
            2 -> Health()
            3 -> Science()
            4 -> Sports()
            5 -> Technology()
            else -> Business()
        }

    }


    override fun getCount(): Int  = NUM_OF_FRAGMENT

    override fun getPageTitle(position: Int): CharSequence? {

        return when(position){
            0 -> Business::class.java.simpleName
            1 -> Entertainment::class.java.simpleName
            2 -> Health::class.java.simpleName
            3 -> Science::class.java.simpleName
            4 -> Sports::class.java.simpleName
            5 -> Technology::class.java.simpleName
            else -> "Defauld"
        }

    }
}