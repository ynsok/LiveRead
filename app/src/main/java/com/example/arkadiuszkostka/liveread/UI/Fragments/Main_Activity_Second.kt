package com.example.arkadiuszkostka.liveread.UI.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.arkadiuszkostka.liveread.R

class Main_Activity_Second : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       arguments!!.getString(secondID, "")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main__activity__second, container, false)
    }


    companion object {
        const val secondID =  "SecondId"
        fun newInstance(catId: String) = Main_Activity_Second().apply {
            arguments = Bundle().apply {
                putString(secondID, catId)
            }
        }
    }


}
