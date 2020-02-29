package com.example.vgoa;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Review extends AppCompatActivity {

    EditText review,source;
    Button submit1;
    private String Review, Source,Name;
    private static final String KEY_EMPTY = "";

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        review=(EditText) findViewById(R.id.review);
        source=(EditText) findViewById(R.id.source);
        submit1=(Button)findViewById(R.id.submitb);

        if(submit1!=null)
            submit1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (view == submit1) {
                        if(haveNetwork())
                            registerUser();
                        else if (!haveNetwork()){
                            Toast.makeText(Review.this,"No network",Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });

    }

    private void registerUser() {
        if (validateInputs()) {
            Review = review.getText().toString().trim();
            Source = source.getText().toString().trim();
            Name="ABC";
            System.out.println(Review+Source+Name);
            //dialog.setMessage("Submitting your Review...");
            //dialog.show();

            JSONObject request = new JSONObject();
            try {
                //Populate the request parameters
                request.put("review", Review);
                request.put("source",Source);
                request.put("name",Name);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            JsonObjectRequest stringRequest = new JsonObjectRequest
                    (Request.Method.POST, Constants.REGISTER_URL, request, new Response.Listener<JSONObject>() {
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
                                    System.out.println(response.getString("message")+"...............dbelseerror........................");

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
        if (KEY_EMPTY.equals(Review)) {
            review.setError("Review cannot be empty");
            review.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(Source)) {
            source.setError("Source cannot be empty");
            source.requestFocus();
            return false;
        }

        return true;
    }


}
