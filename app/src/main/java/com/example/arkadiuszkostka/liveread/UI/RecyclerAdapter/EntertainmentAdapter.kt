package com.example.arkadiuszkostka.liveread.UI.RecyclerAdapter

import android.annotation.SuppressLint
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.arkadiuszkostka.liveread.BR
import com.example.arkadiuszkostka.liveread.Db.EntertainmentEntry
import com.example.arkadiuszkostka.liveread.Extention.inflate
import com.example.arkadiuszkostka.liveread.Extention.update
import com.example.arkadiuszkostka.liveread.R
import com.example.arkadiuszkostka.liveread.UI.OnItemPosition
import kotlinx.android.synthetic.main.list_item_business.view.*
import kotlin.properties.Delegates

class EntertainmentAdapter(private val listner: OnItemPosition): RecyclerView.Adapter<EntertainmentAdapter.ViewHolder>() {
    var data: List<EntertainmentEntry> by Delegates.observable(emptyList())
    {property, oldValue, newValue -> update(oldValue,newValue){old,new ->old.id == new.id} }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val dataBindingUtil:ViewDataBinding = DataBindingUtil.inflate(parent.inflate()
                , R.layout.list_item_entertainment,parent
                ,false)

        return ViewHolder(dataBindingUtil)

    }

    override fun getItemCount(): Int  = data.size

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(true)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindDataToView(data[position],listner)
        holder.getItemPositionInAdapter(data[position].id)
    }


    class ViewHolder(val binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root) {
        private var id: Int = -1

        fun bindDataToView(data: Any, listner: OnItemPosition) {
            binding.setVariable(BR.data, data)
            binding.executePendingBindings()
            binding.root.list_item_cardView.setOnClickListener { listner.getItemPosition(id) }
        }

        fun getItemPositionInAdapter(id: Int) {
            this.id = id

        }




    }
}