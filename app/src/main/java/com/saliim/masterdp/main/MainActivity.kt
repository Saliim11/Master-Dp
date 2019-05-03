package com.saliim.masterdp.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.Toast
import com.saliim.masterdp.R
import com.saliim.masterdp.adapter.DpIndexAdapter
import com.saliim.masterdp.api.API
import com.saliim.masterdp.create.CreateDpActivity
import com.saliim.masterdp.model.DataDp
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    var tempDatas: java.util.ArrayList<DataDp>? = null

    private var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "List Data DP"

        btn_pindah_insert.setOnClickListener {
            val intent = Intent(this@MainActivity, CreateDpActivity::class.java)
            startActivity(intent)
        }

        getData()

        swipe_refresh.setOnRefreshListener { getData() }

    }

    override fun onResume() {
        super.onResume()
        swipe_refresh.isRefreshing = true
        getData()
        swipe_refresh.setOnRefreshListener { getData() }
    }

    private fun getData() {
        API.getDataDp().enqueue(object : Callback<ArrayList<DataDp>> {
            override fun onResponse(call: Call<ArrayList<DataDp>>, response: Response<ArrayList<DataDp>>) {
                if (response.code() == 200){
                    tempDatas = response.body()
                    Log.i("dataIndex", "" + tempDatas)

                    if (tempDatas == null){
                        txt_null_data.visibility = View.VISIBLE
                    } else {
                        txt_null_data.visibility = View.GONE
                        recycler?.setHasFixedSize(true)
                        recycler?.layoutManager = LinearLayoutManager(this@MainActivity)
                        recycler?.adapter = DpIndexAdapter(tempDatas)
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Gagal", Toast.LENGTH_LONG).show()
                }
                swipe_refresh.isRefreshing = false
            }

            override fun onFailure(call: Call<ArrayList<DataDp>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "gagal", Toast.LENGTH_SHORT).show()
                swipe_refresh.isRefreshing = false
            }
        })
    }
}
