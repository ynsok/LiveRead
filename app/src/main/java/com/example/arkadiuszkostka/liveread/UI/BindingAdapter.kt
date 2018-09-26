package com.example.arkadiuszkostka.liveread.UI

import android.annotation.SuppressLint
import android.databinding.BindingAdapter
import android.text.format.DateUtils
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.arkadiuszkostka.liveread.Extention.GONE
import com.example.arkadiuszkostka.liveread.Extention.Visibility
import com.example.arkadiuszkostka.liveread.Extention.logInfo
import java.text.SimpleDateFormat
import java.util.*

object ImageBindingAdapter {
    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageURL(view: ImageView, url: String?) {
        if (url != null) {
            when(url.startsWith("//")){
                true ->  {
                    val newString = "https:$url"
                    logInfo(newString,this)
                    GlideInitialize(view,newString)
                }
                false -> GlideInitialize(view,url)
            }

        }else{
            GONE(view)
        }

    }
    private fun GlideInitialize(view: ImageView,newString: String?){
        Glide.with(view.context).load(newString)
                .apply(RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(view)
        Visibility(view)
    }
    @JvmStatic
    @BindingAdapter("showImage")
    fun setImageToDetail(view: ImageView, url: String?) {
        if (url != null) {
            when(url.startsWith("//")){
                true ->  {
                    val newString = "https:$url"
                    logInfo(newString,this)
                    GlideInitialize(view,newString)
                }
                false -> GlideInitialize(view,url)
            }

        }

    }


    @SuppressLint("SimpleDateFormat")
    @JvmStatic
    @BindingAdapter("showConvertedTime")
    fun convertTime(view: TextView, time: String?) {

        //"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        //parse("2016-01-24T16:00:00.000Z").time
        if(time != null) {
            val pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
            val idOfZone = "GMT"
            val timeChanged = time.substring(0, 19) + ".000Z"
            val dateFormatter = SimpleDateFormat(pattern)
            dateFormatter.timeZone = TimeZone.getTimeZone(idOfZone)
            val timeZone = dateFormatter.parse(timeChanged).time
            val now = System.currentTimeMillis()
            val convertedStatus = DateUtils.getRelativeTimeSpanString(timeZone, now, DateUtils.SECOND_IN_MILLIS)
            view.text = convertedStatus
        }

    }
}