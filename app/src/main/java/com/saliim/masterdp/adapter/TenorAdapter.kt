package com.saliim.masterdp.adapter

import android.content.Context
import android.content.Intent
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.util.AdapterListUpdateCallback
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox

import java.util.ArrayList
import android.widget.TextView
import com.saliim.masterdp.R
import com.saliim.masterdp.create.CreateDpActivity
import com.saliim.masterdp.model.DataTenor
import java.io.Serializable

internal class TenorAdapter(var tempData: ArrayList<DataTenor>) : RecyclerView.Adapter<TenorAdapter.TenorViewHolder>() {
    private val dataSet: List<DataTenor>?

    val checkedTenors = ArrayList<DataTenor>()

    init {
        dataSet = tempData

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): TenorViewHolder {
        val itemView = LayoutInflater.from(viewGroup.context).inflate(com.saliim.masterdp.R.layout.item_list_tenor, viewGroup, false)



        val intent = Intent("custom-message")
        intent.putExtra("quantity", checkedTenors as Serializable)
        LocalBroadcastManager.getInstance(viewGroup.context).sendBroadcast(intent)

        Log.d("checkTenor", ""+checkedTenors)

        return TenorViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TenorViewHolder, i: Int) {
        val dataTenor = tempData[i]

        holder.tenor.text = dataTenor.tenor
        holder.Cbtenor.isChecked = dataTenor.selected

        holder.setItemClickListener(object : TenorViewHolder.ItemClickListener{
            override fun onItemClick(v: View, pos: Int) {
                val myCheckBox = v as CheckBox
                val currentTenor = tempData[pos]

                if (myCheckBox.isChecked){
                    currentTenor.selected = true
                    checkedTenors.add(currentTenor)

                    Log.d("checkTenor2", ""+currentTenor)

                } else if (!myCheckBox.isChecked){
                    currentTenor.selected = false
                    checkedTenors.remove(currentTenor)
                }


            }
        })



    }

    override fun getItemCount(): Int {
        return dataSet?.size ?: 0
    }

    class TenorViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var Cbtenor: CheckBox
        var tenor : TextView

        lateinit var myItemClickListener: ItemClickListener


        init {
            Cbtenor = itemView.findViewById(R.id.cb_tenor)
            tenor = itemView.findViewById(R.id.txt_tenor)

            Cbtenor.setOnClickListener(this)

        }

        fun setItemClickListener(ic: ItemClickListener) {
            this.myItemClickListener = ic
        }

        override fun onClick(v: View) {
            this.myItemClickListener.onItemClick(v, layoutPosition)
        }

        interface ItemClickListener {
            fun onItemClick(v: View, pos: Int)
        }
    }
}
