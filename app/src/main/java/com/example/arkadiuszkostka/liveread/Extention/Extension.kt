package com.example.arkadiuszkostka.liveread.Extention

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.arkadiuszkostka.liveread.Db.*
import com.example.arkadiuszkostka.liveread.ViewModel.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Retrofit


inline fun <reified T> Retrofit.create(): T {

    return this.create(T::class.java)
}

fun logInfo(infoMessage: String, T: Any) {
    Log.i(T::class.java.simpleName, infoMessage)


}

fun ViewGroup.inflate(): LayoutInflater {

    return LayoutInflater.from(context)
}
fun  initialKeywordViewModel(fragment: Fragment): LiveData<List<KeywordEntry>>{
    return ViewModelProviders.of(fragment).get(MainActivityViewModelSecond::class.java).getDataByKeyword()
}

 fun initialBusinessViewModel(fragment: Fragment): LiveData<List<BusinessEntry>> {
    return ViewModelProviders.of(fragment).get(BusinessViewModel::class.java)
            .getNewsArticleFromWeb()
}
fun initialEntertainmentViewModel(fragment: Fragment): LiveData<List<EntertainmentEntry>> {
    return ViewModelProviders.of(fragment).get(EntertainmentViewModel::class.java)
            .getEnterainmentData()
}
fun initialHealthViewModel(fragment: Fragment): LiveData<List<HealthEntry>> {
    return ViewModelProviders.of(fragment).get(HealthViewModel::class.java)
            .getHealthData()
}
fun initialScienceViewModel(fragment: Fragment): LiveData<List<ScienceEntry>> {
    return ViewModelProviders.of(fragment).get(ScienceViewModel::class.java)
            .getDataFromScienceDB()
}

fun initialSportsViewModel(fragment: Fragment): LiveData<List<SportsEntry>> {
    return ViewModelProviders.of(fragment).get(SportsViewModel::class.java)
            .getSportsDB()
}
fun initialTechnologyViewModel(fragment: Fragment): LiveData<List<TechnologyEntry>> {
    return ViewModelProviders.of(fragment).get(TechnologyViewModel::class.java)
            .getTechnologyData()
}

fun GONE(view:View): View {
    view.visibility = View.GONE
    return view
}
fun Visibility(view:View):View{
    view.visibility = View.VISIBLE
    return view
}
inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object: TypeToken<T>() {}.type)!!


 fun  <T>LiveData<T>.updateUI(owner: LifecycleOwner, observer: (t:T) -> Unit){
    this.observe(owner,android.arch.lifecycle.Observer {
        it?.let {observer}
    })
}
inline fun <reified T> RecyclerView.Adapter<*>.update(old: List<T>, new: List<T>, crossinline compare:(T, T)->Boolean){
    val diffUttil =  DiffUtil.calculateDiff(object : DiffUtil.Callback(){
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return compare(old[oldItemPosition],new[newItemPosition])
        }

        override fun getOldListSize(): Int {
            return old.size
        }

        override fun getNewListSize(): Int {
            return  new.size
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return old[oldItemPosition] == new[newItemPosition]
        }

    })
    diffUttil.dispatchUpdatesTo(this)
}


