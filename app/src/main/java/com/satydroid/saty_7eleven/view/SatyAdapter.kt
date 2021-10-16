package com.satydroid.saty_7eleven.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.satydroid.saty_7eleven.R

class SatyAdapter(val clickListener: OnItemClickListener,  var namesList: ArrayList<String> = ArrayList<String>()) : RecyclerView.Adapter<SatyAdapter.MyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.names_rv_layout, parent, false)
        return MyViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.names_rv_tv.text =(namesList[position])


        holder.itemView.setOnClickListener {
            clickListener.onItemEditCLick(namesList[position])
        }

    }

    override fun getItemCount(): Int {
        return namesList.size
    }

    interface OnItemClickListener {
        fun onItemEditCLick(name : String)
    }


    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
         var names_rv_tv : TextView = view.findViewById(R.id.names_rv_tv)
    }
}
