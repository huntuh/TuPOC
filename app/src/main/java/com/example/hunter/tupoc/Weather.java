package com.example.hunter.tupoc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.ImageButton;

public class Weather extends AppCompatActivity {

    private Button hourlyButton;
    private Button dailyButton;
    private Button weeklyButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);


        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                finish();
            }
        });

        Button hourlyButton = (Button) findViewById(R.id.button);
        hourlyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Weather.this, WeatherView.class);
                startActivity(intent);
            }
        });

    }
}
