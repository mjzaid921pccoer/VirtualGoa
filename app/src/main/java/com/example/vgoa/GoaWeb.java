package com.example.vgoa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class GoaWeb extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goa_web);

        webView = findViewById(R.id.mywebview);
        webView.setWebViewClient(new WebViewClient());
        //webView.loadUrl("http://192.168.43.117/copygram/index.php");
        webView.loadUrl("https://goa-tourism.com/GTDC-Panoramas/Goa%20Tourism/BagaBeach/BagaBeach");
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }
    /*@Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }else{
            super.onBackPressed();
        }

    }*/

}
