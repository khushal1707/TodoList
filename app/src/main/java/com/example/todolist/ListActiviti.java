package com.example.todolist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.todolist.data.DatabaseHandler;
import com.example.todolist.model.Task;
import com.example.todolist.ui.RecyclerViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class ListActiviti extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<Task> taskList;
    private DatabaseHandler databaseHandler;
    private FloatingActionButton fab;

    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private Button saveButton;
    private EditText tasks;
    private EditText startime ;
    private EditText deadline;
    private EditText status;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_activiti);
        
        recyclerView =findViewById(R.id.recyclerview);
        fab = findViewById(R.id.fab);

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

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPopup();

            }
        });
    }

    private void createPopup() {
        builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.popup,null);

        tasks = view.findViewById(R.id.tasks_pop);
        startime =view.findViewById(R.id.starttime_pop);
        deadline = view.findViewById(R.id.deadline_pop);
        status = view.findViewById(R.id.status_pop);
        saveButton = view.findViewById(R.id.saveStatus);

        builder.setView(view);
        dialog = builder.create();
        dialog.show();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!tasks.getText().toString().isEmpty() &&
                        !startime.getText().toString().isEmpty() &&
                        !deadline.getText().toString().isEmpty()&&
                        !status.getText().toString().isEmpty()) {
                    saveTask(view);
                }else
                {
                    Snackbar.make(view,"Empty Not Allowed",Snackbar.LENGTH_SHORT).show();
                }
            }
        });

    }


        private void saveTask(View view) {
            Task task = new Task();
            String newTask = tasks.getText().toString().trim();
            int starttime = Integer.parseInt(startime.getText().toString().trim());
            int dead = Integer.parseInt(deadline.getText().toString().trim());
            String Status = status.getText().toString().trim();

            task.setTaskname(newTask);
            task.setStarttime(starttime);
            task.setDeadline(dead);
            task.setStatus(Status);

            databaseHandler.addTask(task);

            Snackbar.make(view,"Item Saved",Snackbar.LENGTH_SHORT).show();

            //to delay and dismiss the dialog box and to also got to the new activity
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //code to be run
                    dialog.dismiss();
                    //to move next screen
                    startActivity(new Intent(ListActiviti.this , ListActiviti.class));
                    finish();

                }
            },800);

        }

}