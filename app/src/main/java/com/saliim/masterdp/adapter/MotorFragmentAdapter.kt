package com.saliim.masterdp.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.saliim.masterdp.R
import com.saliim.masterdp.model.DataMotor

class MotorFragmentAdapter(var masterDpAdapter: List<DataMotor>? = null,
                                       val listener: ((DataMotor?) -> Unit)? = null):
    RecyclerView.Adapter<MotorFragmentAdapter.MotorFragmentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MotorFragmentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_motor_fragment, parent, false)
        return MotorFragmentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return masterDpAdapter?.size ?: 0
    }

    override fun onBindViewHolder(holder: MotorFragmentViewHolder, position: Int) {
        holder.masterDpAdapter = masterDpAdapter?.get(position)
    }

    inner class MotorFragmentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var masterDpAdapter: DataMotor? = null
            set(value) {
                itemView.findViewById<TextView>(R.id.txt_kode_motor_frag).text = value?.id
                itemView.findViewById<TextView>(R.id.txt_tipe_motor_frag).text = value?.name
                itemView.findViewById<TextView>(R.id.txt_harga_jual_frag).text = value?.hargaJual
                itemView.setOnClickListener {
                    listener?.invoke(value)
                }
            }

    }
}