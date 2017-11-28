package com.example.hunter.tupoc;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Tasks extends ListActivity {

    ArrayList<Task> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        //initialize buttons
        ImageButton settingsButton = findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setContentView(R.layout.activity_settings);
            }
        });

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setContentView(R.layout.activity_home_page);
            }
        });
        Button newTaskButton = findViewById(R.id.createTaskButton);
        Button dateButton = findViewById(R.id.dueDateButton);
        Button priorityButton = findViewById(R.id.priorityButton);
        Button abcButton = findViewById(R.id.abcButton);


        /*get tasks from internal storage as a txt file.
         * task info is separated by one backslash (no spaces);
         * <task name> \ <due Date> \ <priority>
         */

    }






}