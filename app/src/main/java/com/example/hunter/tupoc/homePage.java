package com.example.hunter.tupoc;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

public class homePage extends AppCompatActivity {


    // weatherOn , calendarOn , taskOn , light, moderate , busy
    ArrayList<Integer> settings;
    ArrayList<Task> tasks;
    ArrayList<Button> calendarButtons;
    ArrayList<Button> buttons;
    ArrayList<TextView> textViews;
    String[] taskNames;
    int blue = Color.BLUE;
    int red = Color.RED;
    int yellow = Color.YELLOW;
    //these let you know whether to turn the features on or off. | 1 = ON | 0 = OFF |
    int tasksOn;
    int weatherOn;
    int calendarOn;
    //required number of tasks before a certain button background color
    int lightMax; // anything < lightMax will be blue
    int busyMin; //anything > busyMin will be red
    //anything greater than light max but less than busyMin will be yellow






    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);




        try {
            getSettings();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //creates taskList view and array adapter to put items into the task list
        final Button tasksButton = findViewById(R.id.tasksButton);
        tasksButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(homePage.this, Tasks.class);
                startActivity(intent);
            }
        });

        final ListView taskListView = findViewById(R.id.taskListView);

        try {
            getTasks(taskListView);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }



        final Button weatherButton = findViewById(R.id.weatherButton);
        weatherButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(homePage.this, Weather.class);
                startActivity(intent);
            }
        });

        final ImageButton settingsButton = findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(homePage.this, settings.class);
                startActivity(intent);
            }
        });


        /*
         * Inits calendar buttons
         */

        final Button monButton = findViewById(R.id.monButton);
        final Button tuesButton = findViewById(R.id.tuesButton);
        final Button wedButton = findViewById(R.id.wedButton);
        final Button thursButton = findViewById(R.id.thursButton);
        final Button friButton = findViewById(R.id.friButton);
        final Button satButton = findViewById(R.id.satButton);
        final Button sunButton = findViewById(R.id.sunButton);
        //creates calendar button array list for easy referencing
        calendarButtons = new ArrayList<>(Arrays.asList(monButton,tuesButton,wedButton,thursButton,friButton,satButton,sunButton));
        for(Button b:calendarButtons)
        {

            b.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(homePage.this, Calendar.class);
                    startActivity(intent);
                }
            });
        }

        buttons = new ArrayList<>(Arrays.asList(weatherButton,monButton,tuesButton,wedButton,thursButton,friButton,satButton,sunButton,tasksButton));


        /*
         * Inits all text views
         */
        final TextView monView = findViewById(R.id.monView);
        final TextView tuesView = findViewById(R.id.tuesView);
        final TextView wedView = findViewById(R.id.wedView);
        final TextView thursView = findViewById(R.id.thursView);
        final TextView friView = findViewById(R.id.friView);
        final TextView satView = findViewById(R.id.satView);
        final TextView sunView = findViewById(R.id.sunView);
        //creates text view arrayList for easy referencing
        textViews = new ArrayList<>(Arrays.asList(monView,tuesView,wedView,thursView,friView,satView,sunView));

        /*
         * after setting up all ui buttons and textviews and putting them into an arrayList, update their colors
         */

        updateColors(textViews,buttons);



    }


    private void updateColors(ArrayList<TextView> textViews,ArrayList<Button> buttons) {

    }


    /*
     * uses a file input stream to read the tasks from a txt file stored internally in the device. Stores the data as individual task objects in an arraylist
     * @return an ArrayList<Task> with the users tasks.
     * @param filePath ~ a path the the txt file holding the task data
     */
    public void getTasks(ListView taskListView) throws IOException, ParseException {
        FileInputStream fis = openFileInput("tasks");
        if(fis.read() == -1){
            taskNames = new String[0];
        }
        else
        {
            taskNames = new String[0];
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split("\\\\");
                tasks.add(new Task(words[0], words[1], words[2]));
            }

            for(int i = 0; i < tasks.size()-1;i++)
            {
                taskNames[i] = tasks.get(i).getTaskName();
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(taskListView.getContext(),android.R.layout.simple_list_item_1,taskNames);
        taskListView.setAdapter(adapter);
        fis.close();

    }


    /*
     * method to retrieve settings in the form of an Integer Array
     * @return <Weather>(on/off), <Calendar> (on/off), <Tasks> (on/off), <lightColor>, <moderateColor>, <busyColor>
     *                   1 / 0                1 / 0             1 / 0
     */
    public void getSettings() throws IOException {

        FileInputStream fis = openFileInput("settings");
        if(fis.read() == -1)
        {
            settings = new ArrayList<>(Arrays.asList(1,1,1,3,7));
            saveSettings();
        }
        else
        {
            settings = new ArrayList<>();
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                settings.add(Integer.parseInt(line));
            }
        }
        weatherOn = settings.get(0);
        calendarOn = settings.get(1);
        tasksOn = settings.get(2);
        lightMax = settings.get(3);
        busyMin = settings.get(5);
        fis.close();
    }

    /*
     * Saves settings to a file in internal storage. Each settings is stored on its own line in the file.
     */
    private void saveSettings() throws IOException {

        FileOutputStream fos = openFileOutput("settings", Context.MODE_PRIVATE);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        for(int i = 0;i <settings.size()-1;i++)
        {
            String tmp = settings.get(i).toString();
            fos.write(tmp.getBytes());
            if(i != settings.size()-1) {
                bw.newLine();
            }
        }
        bw.close();
        fos.close();
    }
}
