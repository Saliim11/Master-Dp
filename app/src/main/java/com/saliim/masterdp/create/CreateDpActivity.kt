package com.saliim.masterdp.create

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.saliim.masterdp.api.API

import com.saliim.masterdp.model.DataMotorKategori
import kotlinx.android.synthetic.main.activity_create_dp.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.*
import android.graphics.Color
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.*
import com.saliim.masterdp.R
import com.saliim.masterdp.adapter.TenorAdapter
import com.saliim.masterdp.model.DataMotor
import com.saliim.masterdp.model.DataTenor
import com.saliim.masterdp.viewModels.MasterLovDpViewModel
import okhttp3.ResponseBody

class CreateDpActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener{

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    private var toolbar: Toolbar? = null

    var kateg_motor: ArrayList<DataMotorKategori?>? = null

    private var tenor_list: java.util.ArrayList<DataTenor>? = null

    internal var numbers: ArrayList<DataTenor> = ArrayList()

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.saliim.masterdp.R.layout.activity_create_dp)

        toolbar = findViewById<View>(com.saliim.masterdp.R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Create Data DP"

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,  IntentFilter("custom-message"))

        spinner_kategori_motor.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long) {
                val data_kategori_motor = kateg_motor?.get(position)?.id
                Log.d("KATEGORI", "$data_kategori_motor")

                val prefs = this@CreateDpActivity.getSharedPreferences("X", Context.MODE_PRIVATE)
                val editor = prefs.edit()
                editor.putString("kategori", data_kategori_motor)
                editor.commit()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        getKategoriMotor {}

        et_id_motor.isEnabled = false
        et_id_motor.setTextColor(Color.BLACK)

        val masterLovViewModel = ViewModelProviders.of(this).get(MasterLovDpViewModel::class.java)

        masterLovViewModel.selectedMasterLovDp.observe(this, Observer {
            onSelectMasterIdMotor(it)
        })

        val pref = this@CreateDpActivity.getSharedPreferences("X", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.clear()

        getTenor()

        btn_submit_add.setOnClickListener {
            val inputIM = et_id_motor.text.toString()
            val inputJD = et_jumlah_dp.text.toString()

            when {
                inputIM.isEmpty() -> et_id_motor.error = "pilih id motor terlebih dahulu"
                inputJD.isEmpty() -> et_jumlah_dp.error = "isi jumlah dp terlebih dahulu"
                else -> doAdd()
            }
        }

    }

    var mMessageReceiver: BroadcastReceiver = object:BroadcastReceiver() {
        override fun onReceive(context:Context, intent: Intent) {
            // Get extra data included in the Intent
            val qty = intent.getSerializableExtra("quantity") as ArrayList<DataTenor>
//            Toast.makeText(this@CreateDpActivity, "cek $qty", Toast.LENGTH_SHORT).show()
//            Log.d("CEK ", ""+qty)

            numbers = qty
        }
    }

    fun getKategoriMotor(onFinish: () -> Unit){
        API.getDataKategoriMotor().enqueue(object : Callback<ArrayList<DataMotorKategori>>{
            override fun onResponse(call: Call<ArrayList<DataMotorKategori>>, response: Response<ArrayList<DataMotorKategori>>) {
                if (response.code() == 200){
                    kateg_motor = ArrayList()
                    kateg_motor?.add(0, null)
                    response.body()?.forEach { kateg_motor?.add(it) }
                    val adapter = CustomAdapter<DataMotorKategori?>(this@CreateDpActivity,
                        com.saliim.masterdp.R.layout.spinner_custom, com.saliim.masterdp.R.layout.spinner_dropdown_item,
                        kateg_motor?.toTypedArray()!!)

                    Log.d("kategMotor", ""+kateg_motor)

                    spinner_kategori_motor.adapter = adapter

                    onFinish()
                }else{
                    Toast.makeText(this@CreateDpActivity, "Error", Toast.LENGTH_LONG).show()

                }
            }

            override fun onFailure(call: Call<ArrayList<DataMotorKategori>>, t: Throwable) {
                Toast.makeText(this@CreateDpActivity, "Error", Toast.LENGTH_LONG).show()
            }
        })
    }


    fun getMotor(view: View){
        DataMotorFragment().show(supportFragmentManager, "")
    }


    private fun onSelectMasterIdMotor(posCode: DataMotor?) {
        et_id_motor.setText(posCode?.id)
    }


    private fun getTenor() {
        API.getDataTenor().enqueue(object : Callback<ArrayList<DataTenor>>{
            override fun onResponse(call: Call<ArrayList<DataTenor>>, response: Response<ArrayList<DataTenor>>) {
                if (response.code() == 200){
                    tenor_list = response.body()

                    if (tenor_list == null){
                        txt_null_tenor.visibility = View.VISIBLE
                        Toast.makeText(this@CreateDpActivity, "Tenor Tidak Ada", Toast.LENGTH_LONG).show()
                    } else {
                        txt_null_tenor.visibility = View.GONE

                        val rv = findViewById<RecyclerView>(R.id.recycler_tenor)
                        rv.layoutManager = LinearLayoutManager(this@CreateDpActivity)
                        rv.itemAnimator = DefaultItemAnimator()
                        rv.setHasFixedSize(true)

                        //SET ADAPTER
                        rv.adapter = TenorAdapter(tenor_list!!)
                    }
                } else {
                    Toast.makeText(this@CreateDpActivity, "Tenor Tidak Ada", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ArrayList<DataTenor>>, t: Throwable) {
                Toast.makeText(this@CreateDpActivity, "Tenor Tidak Ada", Toast.LENGTH_LONG).show()
            }
        })
    }


    private fun doAdd() {
        val progressDialog = ProgressDialog(this@CreateDpActivity)
        progressDialog.setMessage("Creating...\nplease check the tenor if this isn't completed")
        progressDialog.show()


        Log.d("cekMessage", "$numbers")

        val id_motor = et_id_motor.text.toString()
        val jumlah_dp = et_jumlah_dp.text.toString()

        val create_by = "1"

            API.addDataDp(id_motor, jumlah_dp, numbers, create_by).enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (response.code() == 200){
                        Log.i("insert", "" + response.body())
                        Toast.makeText(this@CreateDpActivity, "sukses", Toast.LENGTH_SHORT).show()
                        progressDialog.dismiss()
                        finish()
                    } else {
                        Toast.makeText(this@CreateDpActivity, "gagal1", Toast.LENGTH_LONG).show()
                        progressDialog.dismiss()
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Toast.makeText(this@CreateDpActivity, "gagal2", Toast.LENGTH_LONG).show()
                    progressDialog.dismiss()
                }
            })

    }


    class CustomAdapter<T>(context: CreateDpActivity, val viewResourceId: Int, val dropDownReourceId: Int, val list: Array<T>) : ArrayAdapter<T>(context, viewResourceId, dropDownReourceId, list) {

        internal var layoutInflater: LayoutInflater = context.layoutInflater

        override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
            return getCustomView(position, convertView, parent, dropDownReourceId)
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            return getCustomView(position, convertView, parent, viewResourceId)
        }


        fun getCustomView(position: Int, convertView: View?, parent: ViewGroup?, resourceId: Int): View {

            var view = convertView

            if (view == null) {
                view = layoutInflater.inflate(resourceId, parent, false)
            }

            val textView = view as? TextView
            if (list[position] != null) {
                textView?.text = list[position].toString()
            } else {
                textView?.text = "Pilih Kategori Motor"
            }

            return view!!
        }

        override fun isEnabled(position: Int): Boolean {
            return position != 0
        }

    }



}

