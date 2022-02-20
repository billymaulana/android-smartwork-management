package com.smartworks.smartwork.MenuEmployee.Adapter.viewholder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smartworks.smartwork.R;

public class TaskViewHolder extends RecyclerView.ViewHolder {

    public TextView tvTitle, tvDate;
    public LinearLayout LinearTask;

    public TaskViewHolder(View view) {
        super(view);
        LinearTask   = view.findViewById(R.id.linear_task);
        tvTitle       = view.findViewById(R.id.title);
        tvDate    = view.findViewById(R.id.date);
    }
}
