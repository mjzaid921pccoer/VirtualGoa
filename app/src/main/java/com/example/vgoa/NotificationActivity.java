package com.example.vgoa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class NotificationActivity extends AppCompatActivity {

    TextView notification;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        notification = findViewById(R.id.tv_notification);
        //webView = findViewById(R.id.mywebview);
        String msg = getIntent().getStringExtra("message");
        notification.setText(msg);

        /*notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NotificationActivity.this,Noti.class);
                startActivity(i);
            }
        });*/
        /*
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://192.168.43.117/copygram/index.php");
        webView.loadUrl("https://goa-tourism.com/panorama");
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);*/

    }
    /*
    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }else{
            super.onBackPressed();
        }
    }*/

}
