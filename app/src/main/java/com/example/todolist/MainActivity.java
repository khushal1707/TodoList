package com.example.todolist;

import android.os.Bundle;

import com.example.todolist.data.DatabaseHandler;
import com.example.todolist.model.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private Button saveButton;
    private EditText tasks;
    private EditText startime ;
    private EditText deadline;
    private EditText status;
    private DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        databaseHandler = new DatabaseHandler(this);

        //checking if task is saved

        List<Task> tasks = databaseHandler.getAllTasks();
        for(Task task :tasks){
            Log.d("Main", "onCreate: " + task.getTaskname());
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPopup();
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
    }

    private void createPopup() {
        builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.popup,null);
        tasks = view.findViewById(R.id.tasks);
        startime =view.findViewById(R.id.starttime);
        deadline = view.findViewById(R.id.deadline);
        status = view.findViewById(R.id.status);
        saveButton = view.findViewById(R.id.saveStatus);
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

        builder.setView(view);
        dialog = builder.create();
        dialog.show();

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

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}