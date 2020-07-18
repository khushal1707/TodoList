package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.todolist.data.DatabaseHandler;
import com.example.todolist.model.Task;
import com.example.todolist.ui.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListActiviti extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<Task> taskList;
    private DatabaseHandler databaseHandler;





    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_activiti);
        
        recyclerView =findViewById(R.id.recyclerview);

        databaseHandler = new DatabaseHandler(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        taskList = new ArrayList<>();

        taskList = databaseHandler.getAllTasks();

        /*for(Task task : taskList){

        }*/
        recyclerViewAdapter = new RecyclerViewAdapter(this,taskList);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();
    }
}