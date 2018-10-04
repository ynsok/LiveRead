package com.example.arkadiuszkostka.liveread.UI

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.arkadiuszkostka.liveread.BR
import com.example.arkadiuszkostka.liveread.Db.NewsBase
import com.example.arkadiuszkostka.liveread.Extention.logInfo
import com.example.arkadiuszkostka.liveread.InjectionUtil
import com.example.arkadiuszkostka.liveread.R
import com.example.arkadiuszkostka.liveread.UI.Fragments.*
import com.example.arkadiuszkostka.liveread.ViewModel.DetailViewModel
import com.example.arkadiuszkostka.liveread.databinding.ActivityDetailBinding
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private var urlTOPage: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mBinding: ActivityDetailBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_detail)
        setSupportActionBar(mBinding.toolbarDetail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        mBinding.collapsingDetail.setCollapsedTitleTypeface(Typeface.SERIF)
        mBinding.collapsingDetail.setBackgroundResource(R.drawable.text_view_background)

        val nameOfCategory = intent.getStringExtra(MainActivity.NAME_OF_CATEGORY)
        val positionItemInAdapter = intent.getIntExtra(MainActivity.POSITION, -1)

        val viewModelFactory = InjectionUtil.provideDetailViewModelFactory(applicationContext, positionItemInAdapter, nameOfCategory)
        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel::class.java)

        when (nameOfCategory) {
            Business.CATEGORY -> viewModel.businessData?.observe(this, Observer {
                if (it != null) {
                    logInfo(it.author.toString(), this)

                    takeDataFromDB(it, mBinding, nameOfCategory)
                }
            })

            Entertainment.CATEGORY -> viewModel.entertainmentData?.observe(this, Observer {
                if (it != null) {
                    logInfo(it.author.toString(), this)
                    takeDataFromDB(it, mBinding, nameOfCategory)
                }
            })
            Health.CATEGORY -> viewModel.healthData?.observe(this, Observer {
                if (it != null) {
                    logInfo(it.author.toString(), this)

                    takeDataFromDB(it, mBinding, nameOfCategory)
                }
            })

            Science.CATEGORY -> viewModel.scienceData?.observe(this, Observer {
                if (it != null) {
                    logInfo(it.author.toString(), this)

                    takeDataFromDB(it, mBinding, nameOfCategory)
                }
            })


            Sports.CATEGORY -> viewModel.sportsData?.observe(this, Observer {
                if (it != null) {
                    logInfo(it.author.toString(), this)

                    takeDataFromDB(it, mBinding, nameOfCategory)
                }
            })

            Technology.CATEGORY -> viewModel.technologyData?.observe(this, Observer {
                if (it != null) {
                    logInfo(it.author.toString(), this)
                    takeDataFromDB(it, mBinding, nameOfCategory)
                }
            })
            Main_Activity_Second.CATEGORY -> viewModel.keywordData?.observe(this, Observer {
                if (it != null) {
                    logInfo(it.author.toString(), this)

                    takeDataFromDB(it, mBinding, it.nameOfSource)
                }
            })


        }
    }

    fun openWebView(view: View) {
        val intent = Intent(this, WebView::class.java)
        intent.putExtra(URL_PAGE, urlTOPage)
        logInfo("Intent + $urlTOPage", this)
        startActivity(intent)
    }

    fun shareAction(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        val share_body = takeTitle() + takeAuthor() + takeURL()
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, share_body)
        intent.putExtra(Intent.EXTRA_SUBJECT, takeTitle())
        startActivity(Intent.createChooser(intent, "Share article with..."))
    }

    private fun takeAuthor(): String {
        if (author_detail.text == null) return ""
        return "@" + author_detail.text + " - "


    }

    private fun takeTitle(): String {
        if (title_detail.text == null) return ""
        return "\"" + title_detail.text + "\""
    }

    private fun takeURL(): String {
        if (false) return ""
        return urlTOPage
    }


    private fun <T : NewsBase> takeDataFromDB(data: T, mBinding: ActivityDetailBinding, category: String?) {

        mBinding.setVariable(BR.detail, data)
        mBinding.executePendingBindings()
        urlTOPage = data.url.toString()
        mBinding.collapsingDetail.title = category

        if (data.urlToImage == null) {
            mBinding.appbarDetail.setExpanded(false, true)
        }

    }

    companion object {
        const val URL_PAGE = "urlToPage"
    }
}
