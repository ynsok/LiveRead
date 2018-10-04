package com.example.arkadiuszkostka.liveread.UI.Fragments


import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.arkadiuszkostka.liveread.Extention.logInfo
import com.example.arkadiuszkostka.liveread.R
import com.example.arkadiuszkostka.liveread.UI.PagerAdapter
import com.example.arkadiuszkostka.liveread.databinding.FragmentMainActivityOneBinding
import kotlinx.android.synthetic.main.fragment_main__activity__one.*


class Main_Activity_One : Fragment() {
    private val BUNDLE = "Bundle"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments!!.getString(Main_Activity_One.oneIDN, "")
        retainInstance = true
        logInfo("onCreate", this)

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentMainActivityOneBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main__activity__one, container, false)

        binding.mViewPager.adapter = PagerAdapter(childFragmentManager)
        binding.mTablayout.setupWithViewPager(binding.mViewPager, true)
        logInfo("onCreateView", this)



        if (savedInstanceState != null) {
            binding.mViewPager.currentItem = savedInstanceState.getInt(BUNDLE)

        }
        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(BUNDLE, mViewPager.currentItem)
        logInfo(mViewPager.currentItem.toString() + "onSave", this)

    }

    companion object {
        const val oneIDN = "oneID"
        fun newInstance(one: String) = Main_Activity_One().apply {
            arguments = Bundle().apply {
                putString(oneIDN, one)
            }
        }
    }

}
