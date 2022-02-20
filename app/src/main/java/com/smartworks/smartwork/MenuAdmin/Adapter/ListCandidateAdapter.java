package com.smartworks.smartwork.MenuAdmin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smartworks.smartwork.MenuAdmin.Adapter.viewholder.CandidateViewHolder;
import com.smartworks.smartwork.MenuAdmin.DetailCandidateActivity;
import com.smartworks.smartwork.MenuEmployee.Adapter.viewholder.TaskViewHolder;
import com.smartworks.smartwork.MenuEmployee.DetailTaskActivity;
import com.smartworks.smartwork.R;
import com.smartworks.smartwork.base.config.response.candidate.DataCandidateResponse;
import com.smartworks.smartwork.base.config.response.work.DataWorkResponse;

import java.util.List;


public class ListCandidateAdapter extends RecyclerView.Adapter<CandidateViewHolder> {

    private final List<DataCandidateResponse> dataCandidateResponses;
    private final Context context;

    public ListCandidateAdapter(Context context, List<DataCandidateResponse> responseCandidate) {
        this.context = context;
        this.dataCandidateResponses = responseCandidate;
    }

    @NonNull
    @Override
    public CandidateViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_candidate, viewGroup, false);
        return new CandidateViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CandidateViewHolder holder, int position) {
        DataCandidateResponse dataCandidateResponse = dataCandidateResponses.get(position);
        holder.namaCandidate.setText(dataCandidateResponse.getNama());

        holder.linearCandidate.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailCandidateActivity.class);
            intent.putExtra("nama", dataCandidateResponse.getNama());
            intent.putExtra("email", dataCandidateResponse.getEmail());
            intent.putExtra("alamat", dataCandidateResponse.getAddress());
            intent.putExtra("tglultah", dataCandidateResponse.getBirthdate());
            intent.putExtra("jk", dataCandidateResponse.getJenis_kelamin());
            intent.putExtra("nohp", dataCandidateResponse.getPhone());
            intent.putExtra("cv", dataCandidateResponse.getFiles());
            intent.putExtra("id", dataCandidateResponse.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
       return dataCandidateResponses.size();
    }
}
