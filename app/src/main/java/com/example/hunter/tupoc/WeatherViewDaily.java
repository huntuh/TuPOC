package com.example.hunter.tupoc;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Mark on 11/28/2017.
 */

public class WeatherViewDaily extends AppCompatActivity {

    private WebView webView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_weather);

        webView = (WebView) findViewById(R.id.Webview);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://weather.com/weather/5day/l/USLA0033:1:US");

    }
}
