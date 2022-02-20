package com.smartworks.smartwork.MenuAdmin.Adapter.viewholder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smartworks.smartwork.R;

public class CandidateViewHolder extends RecyclerView.ViewHolder {

    public TextView namaCandidate;
    public LinearLayout linearCandidate;

    public CandidateViewHolder(View view) {
        super(view);
        linearCandidate     = view.findViewById(R.id.linear_candidate);
        namaCandidate       = view.findViewById(R.id.nama);

    }
}
