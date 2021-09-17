package com.philoxenic.ithute.adapter

import android.icu.number.NumberRangeFormatter.with
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.philoxenic.ithute.R
import com.philoxenic.ithute.model.MainItem
import com.squareup.picasso.Picasso

class MainAdapter(val mainitems: ArrayList<MainItem> ): RecyclerView.Adapter<MainAdapter.ViewHolder>(){

    var onItemClick: ((MainItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
        holder.bindItems(mainitems[position])

    }

    override fun getItemCount(): Int {
        return mainitems.size
    }

    inner class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(mainitems[adapterPosition])
            }
        }


        fun bindItems(mainitems: MainItem){
            val taskName = itemView.findViewById(R.id.tvTask) as TextView
            val taskDescription = itemView.findViewById<TextView>(R.id.tvDescription)
            val taskIcon = itemView.findViewById<ImageView>(R.id.icon)
            taskName.text = mainitems.task
            taskDescription.text = mainitems.description
            taskIcon.setImageResource(mainitems.icon)

        }
    }

}