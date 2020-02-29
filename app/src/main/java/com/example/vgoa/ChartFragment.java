package com.example.vgoa;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Float.parseFloat;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChartFragment extends Fragment {
    private BarChart barChart;
    private PieChart pieChart;

    public ChartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_chart, container, false);
        barChart=view.findViewById(R.id.barChart_id);
        pieChart=view.findViewById(R.id.pieChart_id);
        System.out.println("from oncreateView Chart Fragment");
        //getRateChart(getArguments().getString("method"));

        getRateChart();

        barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

                System.out.println("Entry>>"+e+":"+e.toString().length()+"\n"+"Highlight>>"+h);
                int size=e.toString().length();
                String Question=e.toString().substring(10,size-10);
                String Answer=e.toString().substring(size-4,size);
                String DisplayResult="Question:"+Question+"?\n\nAnswer:"+Answer;
                Toast.makeText(getActivity(),DisplayResult,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });

        return view;
    }

    public void getRateChart(){
        String jsonString = "[{'location':'Church'}]";
        JSONArray jsonArray = null;

        try {
            jsonArray = new JSONArray(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }



        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.POST, Constants.FETCH_URL, jsonArray, new com.android.volley.Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                System.out.println(".Response......."+response);
                List<BarEntry> barEntries=new ArrayList<>();
                double x1,x2,x3,x4,x5,x6,x7;
                try {
                    JSONObject obj = response.getJSONObject(0);
                    x1=obj.getDouble("Q1");
                    x2=obj.getDouble("Q2");
                    x3=obj.getDouble("Q3");
                    x4=obj.getDouble("Q4");
                    x5=obj.getDouble("Q5");
                    x6=obj.getDouble("Q6");
                    x7=obj.getDouble("Q6");
                    BarEntry barEntry1 = new BarEntry(1,(float)x1);
                    BarEntry barEntry2 = new BarEntry(2,(float)x2);
                    BarEntry barEntry3 = new BarEntry(3,(float)x3);
                    BarEntry barEntry4 = new BarEntry(4,(float)x4);
                    BarEntry barEntry5 = new BarEntry(5,(float)x5);
                    BarEntry barEntry6 = new BarEntry(6,(float)x6);
                    BarEntry barEntry7 = new BarEntry(7,(float)x7);
                    barEntries.add(barEntry1);
                    barEntries.add(barEntry2);
                    barEntries.add(barEntry3);
                    barEntries.add(barEntry4);
                    barEntries.add(barEntry5);
                    barEntries.add(barEntry6);
                    barEntries.add(barEntry7);

                    BarDataSet barDataSet=new BarDataSet(barEntries,"Rate");
                    barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                    //
                    barDataSet.setBarShadowColor(Color.GRAY);
                    barDataSet.setValueTextSize(20);
                    //barDataSet.setStackLabels();
                    //
                    BarData barData=new BarData(barDataSet);
                    barData.setBarWidth(0.9f);
                    System.out.println("from onresponse of get rate chart() 4");
                    barChart.setVisibility(View.VISIBLE);
                    barChart.animateY(1000);
                    barChart.setData(barData);
                    barChart.setFitBars(true);

                    Description description=new Description();
                    description.setText("Growth Rate Per Annum");
                    barChart.setDescription(description);
                    barChart.invalidate();

                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("......errorrrrrrrr/........"+error);
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(arrayRequest);
    }

}
