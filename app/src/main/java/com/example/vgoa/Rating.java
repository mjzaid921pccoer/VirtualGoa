package com.example.vgoa;

import androidx.appcompat.app.AppCompatActivity;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Rating extends AppCompatActivity {

    RatingBar q1,q2,q3,q4,q5,q6,q7,q8;
    float rate1,rate2,rate3,rate4,rate5,rate6,rate7,rate8;
    String comment,Name,date,time,location;
    private static final String KEY_EMPTY = "";
    EditText commented;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        q1=(RatingBar)findViewById(R.id.ratingBar1);
        q2=(RatingBar)findViewById(R.id.ratingBar2);
        q3=(RatingBar)findViewById(R.id.ratingBar3);
        q4=(RatingBar)findViewById(R.id.ratingBar4);
        q5=(RatingBar)findViewById(R.id.ratingBar5);
        q6=(RatingBar)findViewById(R.id.ratingBar6);
        q7=(RatingBar)findViewById(R.id.ratingBar7);
        q8=(RatingBar)findViewById(R.id.ratingBar8);

        commented=(EditText)findViewById(R.id.comment);

        submit=(Button)findViewById(R.id.submitb);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rate1= q1.getRating();
                rate2= q2.getRating();
                rate3= q3.getRating();
                rate4= q4.getRating();
                rate5= q5.getRating();
                rate6= q6.getRating();
                rate7= q7.getRating();
                rate8= q8.getRating();

                comment=commented.getText().toString();

                Calendar cal=Calendar.getInstance();
                date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
                time = mdformat.format(cal.getTime());


                //System.out.println("DATE:"+date+"TIME"+time);
                //System.out.println("..."+rate1+"...."+rate2+"..."+rate3+"...."+rate4+"...."+rate5+"..."+rate6+"..."+rate7+"...."+rate8+"....."+comment);

                if (view == submit) {
                    if(haveNetwork())
                        registerUser();
                    else if (!haveNetwork()){
                        Toast.makeText(Rating.this,"No network",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }

    private void registerUser() {
        if (validateInputs()) {

            Name="ABC";
            location="Church";

            JSONObject request = new JSONObject();
            try {
                //Populate the request parameters
                request.put("name",Name);
                request.put("location",location);
                request.put("date",date);
                request.put("time",time);
                request.put("rate1", rate1);
                request.put("rate2",rate2);
                request.put("rate3", rate3);
                request.put("rate4",rate4);
                request.put("rate5", rate5);
                request.put("rate6",rate6);
                request.put("rate7", rate7);
                request.put("rate8",rate8);
                request.put("comment",comment);



            } catch (JSONException e) {
                e.printStackTrace();
            }

            JsonObjectRequest stringRequest = new JsonObjectRequest
                    (Request.Method.POST, Constants.RATINGS_URL, request, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            //dialog.dismiss();
                            try {
                                if (response.getInt("status") == 1) {
                                    Toast.makeText(getApplicationContext(),response.getString("message"),Toast.LENGTH_SHORT).show();;
                                    //Intent i = new Intent(Registration.this, MenuNav.class);
                                    //startActivity(i);
                                    System.out.println(response.getString("message")+".............................dberror.........................");

                                } else {
                                    Toast.makeText(getApplicationContext(),
                                            response.getString("message"), Toast.LENGTH_SHORT).show();
                                    System.out.println(response.getString("message")+"...............dbelseerror........................"+response);

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //dialog.dismiss();

                            //Display error message whenever an error occurs
                            Toast.makeText(getApplicationContext(),
                                    error.getMessage(), Toast.LENGTH_SHORT).show();
                            System.out.println(error+":............................errorhai...................................");

                        }
                    });


            RequestQueue requestQueue;
            requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }

    }



    public boolean haveNetwork() {

        boolean have_wifi = false;
        boolean have_mobiledata = false;
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos = cm.getAllNetworkInfo();

        for (NetworkInfo info : networkInfos) {
            if (info.getTypeName().equalsIgnoreCase("WIFI"))
                if (info.isConnected())
                    have_wifi = true;
            if (info.getTypeName().equalsIgnoreCase("MOBILE"))
                if (info.isConnected())
                    have_mobiledata = true;

        }
        return have_mobiledata | have_wifi;
    }

    private boolean validateInputs() {
        if (KEY_EMPTY.equals(rate1)) {
            Toast.makeText(getApplicationContext(),"Please Rate Question 1",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (KEY_EMPTY.equals(rate2)) {
            Toast.makeText(getApplicationContext(),"Please Rate Question 2",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (KEY_EMPTY.equals(rate3)) {
            Toast.makeText(getApplicationContext(),"Please Rate Question 3",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (KEY_EMPTY.equals(rate4)) {
            Toast.makeText(getApplicationContext(),"Please Rate Question 4",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (KEY_EMPTY.equals(rate5)) {
            Toast.makeText(getApplicationContext(),"Please Rate Question 5",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (KEY_EMPTY.equals(rate6)) {
            Toast.makeText(getApplicationContext(),"Please Rate Question 6",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (KEY_EMPTY.equals(rate7)) {
            Toast.makeText(getApplicationContext(),"Please Rate Question 7",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (KEY_EMPTY.equals(rate8)) {
            Toast.makeText(getApplicationContext(),"Please Rate Question 8",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (KEY_EMPTY.equals(comment)) {
            commented.setError("Review cannot be empty");
            commented.requestFocus();
            return false;
        }

        return true;
    }

}
