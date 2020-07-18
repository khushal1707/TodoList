package com.example.todolist.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.todolist.R;
import com.example.todolist.model.Task;

import org.w3c.dom.Text;

import java.text.MessageFormat;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Task> taskList;

    public RecyclerViewAdapter(Context context, List<Task> taskList) {
        this.context = context;
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row,parent,false);
        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.taskName.setText(task.getTaskname());
        holder.starttime.setText(MessageFormat.format("Starting on:  {0}", String.valueOf(task.getStarttime())));
        holder.deadline.setText(MessageFormat.format("Ending on:  {0}", String.valueOf(task.getDeadline())));
        holder.status.setText(MessageFormat.format("Status:  {0}", task.getStatus()));
        holder.dateAdded.setText(MessageFormat.format("Added on:  {0}", task.getDateadded()));


    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView taskName;
        public TextView starttime;
        public TextView deadline;
        public TextView status;
        public TextView dateAdded;
        public Button editButton;
        public Button delete;
        public int id;


        public ViewHolder(@NonNull View itemView,Context ctx) {
            super(itemView);
            context = ctx;
            taskName = itemView.findViewById(R.id.taskName_list);
            starttime = itemView.findViewById(R.id.startime_list);
            deadline= itemView.findViewById(R.id.deadline_list);
            status = itemView.findViewById(R.id.status_list);
            dateAdded = itemView.findViewById(R.id.dateAdded_list);

            editButton = itemView.findViewById(R.id.editButton);
            delete = itemView.findViewById(R.id.deleteBut);

            editButton.setOnClickListener(this);
            delete.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.editButton:

                    break;
                case R.id.deleteBut:
            }
        }
    }
}
