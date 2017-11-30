package com.example.hunter.tupoc;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.util.ArrayList;

public class Tasks extends AppCompatActivity {

    ArrayList<Task> tasks;
    String[] taskNames = new String[0];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        ListView taskListView = findViewById(R.id.taskList);

        try {
            getTasks();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }



        ArrayAdapter<String> adapter = new ArrayAdapter<>(taskListView.getContext(),android.R.layout.simple_list_item_1,taskNames);
        taskListView.setAdapter(adapter);

        //initialize buttons
        ImageButton settingsButton = findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Tasks.this,settings.class);
                startActivity(intent);
            }
        });

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        Button newTaskButton = findViewById(R.id.createTaskButton);
        newTaskButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Tasks.this, CreateTask.class);
                startActivity(intent);
            }
        });
        Button dateButton = findViewById(R.id.dueDateButton);
        Button priorityButton = findViewById(R.id.priorityButton);
        Button abcButton = findViewById(R.id.abcButton);


        /*get tasks from internal storage as a txt file.
         * task info is separated by one backslash (no spaces);
         * <task name> \ <due Date> \ <priority>
         */



    }







    public void getTasks() throws IOException, ParseException {
//        FileInputStream fis = openFileInput("tasks");
//        if(fis.read() == -1){
//            taskNames = new String[0];
//            taskNames[0] = "add new task";
//        }
//        else
//        {
//            taskNames = new String[0];
//            InputStreamReader isr = new InputStreamReader(fis);
//            BufferedReader bufferedReader = new BufferedReader(isr);
//            String line;
//            while ((line = bufferedReader.readLine()) != null) {
//                String[] words = line.split("\\");
//                tasks.add(new Task(words[0], words[1], words[2]));
//            }
//
//            for(int i = 0; i < tasks.size()-1;i++)
//            {
//                taskNames[i] = tasks.get(i).getTaskName();
//            }
//        }
//        fis.close();

    }




}