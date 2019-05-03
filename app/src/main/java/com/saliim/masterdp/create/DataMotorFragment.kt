package com.saliim.masterdp.create

import android.app.Dialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.saliim.masterdp.R
import com.saliim.masterdp.RecyclerItemClickListener
import com.saliim.masterdp.adapter.MotorFragmentAdapter
import com.saliim.masterdp.model.DataMotor
import com.saliim.masterdp.viewModels.MasterLovDpViewModel
import kotlinx.android.synthetic.main.fragment_motor.*

class DataMotorFragment : DialogFragment() {
    lateinit var viewModel: MasterLovDpViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_motor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this.activity!!).get(MasterLovDpViewModel::class.java)

        setupRecyclerView()
        getMasterDp()
        searchEditText.addTextChangedListener(viewModel.onSearch)
        fabClose.setOnClickListener { dialog.dismiss() }

        viewModel.masterLovDp.observe(this, Observer<ArrayList<DataMotor>>{
                    t -> viewModel.tempMasterLovDp.value = t
                if(t?.isNotEmpty() == true){
                    titleDp.visibility = View.VISIBLE
                    recycler_fragment_motor.visibility = View.VISIBLE
                    txt_no_data.visibility = View.GONE
                }else{
                    titleDp.visibility = View.GONE
                    recycler_fragment_motor.visibility = View.GONE
                    txt_no_data.visibility = View.VISIBLE
                }
                progressBar.visibility = View.GONE
            })

            viewModel.tempMasterLovDp.observe(this, Observer {
                (recycler_fragment_motor.adapter as? MotorFragmentAdapter)?.masterDpAdapter = it
                recycler_fragment_motor.adapter!!.notifyDataSetChanged()
            })

            viewModel.errorObservable.observe(this, Observer {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            })

//        recycler_fragment_motor.addOnItemTouchListener(
//            RecyclerItemClickListener(activity, RecyclerItemClickListener.OnItemClickListener { view, position ->
//
//                val intent = Intent(activity, CreateDpActivity::class.java)
//                val data_motor = tempDatas?.get(position)?.name
//                Log.d("dataMotor", "$data_motor")
//
//                intent.putExtra(CreateDpActivity.TIPE_MOTOR, data_motor)
//
//                startActivity(intent)
//            })
//        )

    }

    private fun getMasterDp() {
        val prefs = activity!!.getSharedPreferences("X", Context.MODE_PRIVATE)
        val kategori = prefs.getString("kategori", "")
        Log.d("preference", ""+kategori)
        viewModel.getDataByKategori(kategori.toString())
    }

    override fun onStart() {
        super.onStart()
        val dialog: Dialog = dialog
        if (dialog != null){
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val heigt = (activity!!.resources.displayMetrics.heightPixels*0.70).toInt()
            dialog.window.setLayout(width, heigt)
        }
    }

    private fun setupRecyclerView() {

        recycler_fragment_motor.adapter = MotorFragmentAdapter(listener = { dataMotor ->
            viewModel.selectedMasterLovDp.value = dataMotor
            dismiss()
        })
        recycler_fragment_motor.layoutManager = LinearLayoutManager(activity)
        recycler_fragment_motor.addItemDecoration(DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL))


    }

}