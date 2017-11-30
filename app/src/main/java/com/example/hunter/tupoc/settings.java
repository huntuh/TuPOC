package com.example.hunter.tupoc;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

public class settings extends AppCompatActivity {

    ArrayList<Integer> settings = getSettings();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveData();
                finish();
            }
        });
    }

    private void saveData() {

    }

    public ArrayList<Integer> getSettings() {

        return settings;
    }
}
