package com.smartworks.smartwork.MenuEmployee.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smartworks.smartwork.MenuEmployee.Adapter.viewholder.TaskViewHolder;
import com.smartworks.smartwork.MenuEmployee.DetailTaskActivity;
import com.smartworks.smartwork.R;
import com.smartworks.smartwork.base.config.response.work.DataWorkResponse;

import java.util.List;


public class ListTaskAdapter extends RecyclerView.Adapter<TaskViewHolder> {

    private final List<DataWorkResponse> workResponseList;
    private final Context context;

    public ListTaskAdapter(Context context, List<DataWorkResponse> responseWork) {
        this.context = context;
        this.workResponseList = responseWork;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_task, viewGroup, false);
        return new TaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        DataWorkResponse workResponse = workResponseList.get(position);
        holder.tvTitle.setText(workResponse.getNama());
        holder.tvDate.setText(workResponse.getCreated_at());
        String Status = workResponse.getStatus();
        if (Status.equals("Success")){
            holder.LinearTask.setBackgroundResource(R.drawable.btn_design_green);
        }else{
            holder.LinearTask.setBackgroundResource(R.drawable.btn_design_main);
        }

        holder.LinearTask.setOnClickListener(v -> {
                Intent intent = new Intent(context, DetailTaskActivity.class);
                intent.putExtra("nama", workResponse.getNama());
                intent.putExtra("date", workResponse.getCreated_at());
                intent.putExtra("desc", workResponse.getDeskripsi());
                intent.putExtra("status", workResponse.getStatus());
                intent.putExtra("id", workResponse.getId());
                context.startActivity(intent);
            });
    }

    @Override
    public int getItemCount() {
        return workResponseList.size();
    }
}
