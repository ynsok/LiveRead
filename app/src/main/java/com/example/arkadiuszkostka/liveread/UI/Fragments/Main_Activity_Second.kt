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
import com.example.arkadiuszkostka.liveread.Extention.initialKeywordViewModel
import com.example.arkadiuszkostka.liveread.Extention.logInfo
import com.example.arkadiuszkostka.liveread.R
import com.example.arkadiuszkostka.liveread.UI.DetailActivity
import com.example.arkadiuszkostka.liveread.UI.MainActivity
import com.example.arkadiuszkostka.liveread.UI.OnItemPosition
import com.example.arkadiuszkostka.liveread.UI.RecyclerAdapter.KeywordAdapter
import com.example.arkadiuszkostka.liveread.ViewModel.MainActivityViewModelSecond
import com.example.arkadiuszkostka.liveread.databinding.FragmentMainActivitySecondBinding

class Main_Activity_Second : Fragment(), OnItemPosition {


    private val mAdapter = KeywordAdapter(this)
    private var mSecondViewModel: MainActivityViewModelSecond? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments!!.getString(secondID, "")
        retainInstance = true
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mSecondViewModel = MainActivityViewModelSecond(activity?.application!!)

        initialKeywordViewModel(this).observe(this, Observer {
            logInfo(it?.size.toString(), this)
            if (it != null) {
                mAdapter.data = it
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val mBinding: FragmentMainActivitySecondBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_main__activity__second, container, false)
        mBinding.recyclerViewMainActivitySecond.layoutManager = LinearLayoutManager(context)
        mBinding.recyclerViewMainActivitySecond.adapter = mAdapter
        return mBinding.root
    }


    companion object {
        const val secondID = "SecondId"
        val CATEGORY = Main_Activity_Second::class.java.simpleName!!
        fun newInstance(catId: String) = Main_Activity_Second().apply {
            arguments = Bundle().apply {
                putString(secondID, catId)
            }
        }
    }

    override fun getItemPosition(position: Int) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(MainActivity.POSITION, position)
        intent.putExtra(MainActivity.NAME_OF_CATEGORY, Main_Activity_Second.CATEGORY)
        startActivity(intent)

    }
}
