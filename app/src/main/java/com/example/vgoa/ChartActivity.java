package com.example.vgoa;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        System.out.println("from oncreateChartActivity1");
        String method=getIntent().getStringExtra("method");
        System.out.println("from oncreateChartActivity2 :"+method);
        ChartFragment chartFragment=new ChartFragment();
        System.out.println("from oncreateChartActivity3");
        Bundle bundle=new Bundle();
        System.out.println("from oncreateChartActivity4");
        bundle.putString("method",method);
        System.out.println("from oncreateChartActivity5");
        chartFragment.setArguments(bundle);
        System.out.println("from oncreateChartActivity6");
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer_id,chartFragment).commit();

    }
}
