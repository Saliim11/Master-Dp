package com.saliim.masterdp.viewModels

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.saliim.masterdp.api.API
import com.saliim.masterdp.model.DataMotor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MasterLovDpViewModel : ViewModel(){
    val masterLovDp: MediatorLiveData<ArrayList<DataMotor>> = MediatorLiveData()
    val tempMasterLovDp: MediatorLiveData<ArrayList<DataMotor>> = MediatorLiveData()
    val selectedMasterLovDp = MediatorLiveData<DataMotor>()
    val errorObservable = MediatorLiveData<String>()

    val onSearch: TextWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            val keyword = s.toString().toLowerCase()
            if (keyword == "") return

            val masterLovCustomersResults = masterLovDp.value?.filter {
                it.name.toLowerCase().contains(keyword) }

            tempMasterLovDp.value = ArrayList(masterLovCustomersResults ?: ArrayList())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
    }

    fun getDataByKategori(kategori: String) {
        Log.d("kategori", ""+kategori)

        API.getDataMotor(kategori).enqueue(object : Callback<java.util.ArrayList<DataMotor>> {
            override fun onResponse(call: Call<java.util.ArrayList<DataMotor>>, response: Response<java.util.ArrayList<DataMotor>>) {
                if (response.code() == 200) {
                    masterLovDp.value = response.body()
                }
            }

            override fun onFailure(call: Call<java.util.ArrayList<DataMotor>>, throwable: Throwable) {
                errorObservable.value = "Error"
            }
        })

    }
}