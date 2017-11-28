package com.example.hunter.tupoc;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class homePage extends AppCompatActivity {


    // weatherOn , calendarOn , taskOn , light, moderate , busy
    ArrayList<Integer> settings;
    ArrayList<Task> tasks;
    ArrayList<Button> calendarButtons;
    ArrayList<Button> buttons;
    ArrayList<TextView> textViews;
    int blue = Color.BLUE;
    int red = Color.RED;
    int yellow = Color.YELLOW;
    String tasksFilePath = "tasks";
    String settingsFilePath = "settings";
    //these let you know whether to turn the features on or off. | 1 = ON | 0 = OFF |
    int tasksOn;
    int weatherOn;
    int calendarOn;
    //required number of tasks before a certain button background color
    int light; // anything <= light will be blue
    int moderate; // anything > light but <= moderate will be yellow
    int busy; //anything > busy will be red





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        /*
             try {
            settings = getSettings(settingsFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        weatherOn = settings.get(0);
        calendarOn = settings.get(1);
        tasksOn = settings.get(2);
        light = settings.get(3);
        moderate = settings.get(4);
        busy = settings.get(5);


        /*
         * Set up all buttons and map their click events to their respective activities
         */


        final Button weatherButton = findViewById(R.id.weatherButton);
        weatherButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setContentView(R.layout.activity_calendar);
            }
        });

        final ImageButton settingsButton = findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), settings.class);
                startActivity(intent);
            }
        });


        /*
         * Inits and puts calendar buttons into an array list for easy click event mapping
         */

        final Button monButton = findViewById(R.id.monButton);
        final Button tuesButton = findViewById(R.id.tuesButton);
        final Button wedButton = findViewById(R.id.wedButton);
        final Button thursButton = findViewById(R.id.thursButton);
        final Button friButton = findViewById(R.id.friButton);
        final Button satButton = findViewById(R.id.satButton);
        final Button sunButton = findViewById(R.id.sunButton);
        calendarButtons = new ArrayList<>(Arrays.asList(monButton,tuesButton,wedButton,thursButton,friButton,satButton,sunButton));
        for(Button b:calendarButtons)
        {
            b.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), Calendar.class);
                    startActivity(intent);
                }
            });
        }

        buttons = new ArrayList<>(Arrays.asList(weatherButton,monButton,tuesButton,wedButton,thursButton,friButton,satButton,sunButton));


        /*
         * Sets up all text views
         */

        final TextView monView = findViewById(R.id.monView);
        final TextView tuesView = findViewById(R.id.tuesView);
        final TextView wedView = findViewById(R.id.wedView);
        final TextView thursView = findViewById(R.id.thursView);
        final TextView friView = findViewById(R.id.friView);
        final TextView satView = findViewById(R.id.satView);
        final TextView sunView = findViewById(R.id.sunView);
        textViews = new ArrayList<>(Arrays.asList(monView,tuesView,wedView,thursView,friView,satView,sunView));

        /*
         * after setting up all ui buttons and textviews and putting them into an arrayList, update their colors
         */

        updateColors(textViews,buttons);


        /*
         * Creates a String array of task names to feed into the array adapter for the ListView, and puts all the task objects into an arrayList
         */

        String[] taskNames = new String[0];
        try {
            tasks = getTasks(tasksFilePath);
            for(int i = 0; i < tasks.size()-1;i++)
            {
                taskNames[i] = tasks.get(i).getTaskName();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //creates taskList view and array adapter to put items into the task list
        final ListView taskListView = findViewById(R.id.taskListView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(taskListView.getContext(),android.R.layout.simple_list_item_1,taskNames);
        taskListView.setAdapter(adapter);




    }



    private void updateColors(ArrayList<TextView> textViews,ArrayList<Button> buttons) {

    }


    /*
     * uses a file input stream to read the tasks from a txt file stored internally in the device. Stores the data as individual task objects in an arraylist
     * @return an ArrayList<Task> with the users tasks.
     * @param filePath ~ a path the the txt file holding the task data
     */
    public ArrayList<Task> getTasks(String filePath) throws IOException
    {
        FileInputStream fis = openFileInput(filePath);
        if(fis.read() != -1)
        {
            return null;
        }
        else
        {
            ArrayList<Task> answer = new ArrayList<Task>();
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split("\\\\");
                answer.add(new Task(words[0], words[1], words[2]));
            }
            return answer;
        }

    }


    /*
     * method to retrieve settings in the form of an Integer Array
     * @return <Weather>(on/off), <Calendar> (on/off), <Tasks> (on/off), <lightColor>, <moderateColor>, <busyColor>
     *                   1 / 0                1 / 0             1 / 0
     */
    public ArrayList<Integer> getSettings(String filePath) throws IOException {

        FileInputStream fis = openFileInput(filePath);
        if(fis.read() != -1)
        {
            return null;
        }
        else
        {
            ArrayList<Integer> settings = new ArrayList<>();
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                //removes everything up to the equals sign
                String word = line.replaceFirst(".*=","");
                settings.add(Integer.parseInt(word));
            }
            return settings;
        }
    }
}
