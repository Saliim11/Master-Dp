package com.saliim.masterdp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.saliim.masterdp.R;
import com.saliim.masterdp.model.DataDp;

import java.util.ArrayList;
import java.util.List;

public class DpIndexAdapter extends RecyclerView.Adapter<DpIndexAdapter.DpIndexViewHolder> {
    private List<DataDp> dataSet;

    public DpIndexAdapter(ArrayList<DataDp> tempData) {
        dataSet = tempData;
    }

    @Override
    public DpIndexViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_dp, viewGroup, false);

        return new DpIndexViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DpIndexViewHolder holder, int i) {
        DataDp dataDp = dataSet.get(i);
        holder.txtKode.setText(dataDp.getKode());
        holder.txtDp.setText("Rp."+dataDp.getJumlahDp());
        holder.txtTipeMotor.setText(dataDp.getMotorId());
    }

    @Override
    public int getItemCount() {
        if (dataSet == null){
            return 0;
        } else {
           return dataSet.size();
        }
    }

    public class DpIndexViewHolder extends RecyclerView.ViewHolder {
        public TextView txtKode, txtDp, txtTipeMotor;

        public DpIndexViewHolder(View itemView) {
            super(itemView);
            txtKode = itemView.findViewById(R.id.txt_kode);
            txtDp = itemView.findViewById(R.id.txt_dp);
            txtTipeMotor = itemView.findViewById(R.id.txt_tipe_motor);
        }
    }
}
