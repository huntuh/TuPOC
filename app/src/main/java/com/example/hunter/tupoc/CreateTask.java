package com.example.hunter.tupoc;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.ParseException;

public class CreateTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        ImageButton backButton = (ImageButton)findViewById(R.id.ctBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        TextView nameView = findViewById(R.id.nameView);
        TextView dateView = findViewById(R.id.dateView);
        TextView prioView = findViewById(R.id.prioView);

        final EditText nameText = findViewById(R.id.nameText);
        EditText dateText = findViewById(R.id.dateText);



        //get the spinner from the xml.
        Spinner dropdown = (Spinner)findViewById(R.id.prioSpinner);
        //create a list of items for the spinner.
        String[] items = new String[]{"1", "2", "3","4","5","6","7","8","9","10"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

        Button create = findViewById(R.id.createButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    addNewTask(nameText.getText().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                finish();
            }
        });

    }



    private void addNewTask(String task) throws IOException, ParseException {
        FileOutputStream fos = openFileOutput("tasks", Context.MODE_PRIVATE);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        String tempString = task + "\\" +  "January 1, 2018" + "\\" + 1;

        bw.write(tempString);
        bw.newLine();

        bw.close();
        fos.close();
    }

}