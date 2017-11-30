package com.example.hunter.tupoc;

        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.webkit.WebView;
        import android.webkit.WebViewClient;
/**
 * Created by Mark on 11/28/2017.
 */

public class WeatherView extends AppCompatActivity {

    private WebView webView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_weather);

        webView = (WebView) findViewById(R.id.Webview);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://weather.com/weather/today/l/USLA0033:1:US");

    }
}
