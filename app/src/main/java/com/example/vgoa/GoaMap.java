package com.example.vgoa;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

public class GoaMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private GPSTracker gpsTracker;
    private Location mlocation;
    private LatLng myMarkedLatLng;
    double mlat, mlng;
    JSONArray places;
    Marker marker;
    Set placeSet;
    Button attractions_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goa_map);
        placeSet = new HashSet();
        attractions_btn = findViewById(R.id.attractions_btn);
        gpsTracker = new GPSTracker(getApplicationContext());
        mlocation = gpsTracker.getLocation();
        System.out.println("\n\n................My Location : "+mlocation);
        if(mlocation==null){
            Toast.makeText(this,"Please enable GPS location",Toast.LENGTH_SHORT);
        }
        else{
            mlat = mlocation.getLatitude();
            mlng = mlocation.getLongitude();
            myMarkedLatLng = new LatLng(mlat,mlng);
        }

        //Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (canAccessLocation()) {
            mMap.setMyLocationEnabled(true);
            System.out.println(".................GPS permission given............");
        }

        //mark users current location
        LatLng myLatLng = new LatLng(mlat, mlng);
        mMap.addMarker(new MarkerOptions().position(myLatLng).title("You are here : " + mlat + " , " + mlng));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(15.404049,74.034457),9));
        //mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
/*
        if (places != null)
            for (int i = 0; i < places.length(); i++) {
                try {
                    JSONObject obj = places.getJSONObject(i);
                    msg = obj.getString("message");
                    if (msg.equals("Places fetched")) {
                        System.out.println("....Place :: " + obj.getString("place"));
                        markLocation(obj.getString("place"), obj.getString("latitude"), obj.getString("longitude"));
                        //placeSet.add(obj.getString("place"));
                    }
                    else {
                        System.out.println("Message : " + msg);
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }*/

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if(marker!=null){
                    //marker.remove();
                    mMap.clear();
                    placeSet.clear();
                    System.out.println("\n\n.......Marker removed.......\n\n");
                }
                myMarkedLatLng = latLng;
                marker = mMap.addMarker(new MarkerOptions().position(latLng).title("My Location"));
                System.out.println("\n\nMarker added at :: "+marker);


            }
        });


            attractions_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    marker = mMap.addMarker(new MarkerOptions().position(myMarkedLatLng).title("My Location"));
                    fetchPlacesfromDB();
                }
            });
    }

    private boolean hasPermission(String perm) {
        System.out.println("inside hasPermission............................");
        return(PackageManager.PERMISSION_GRANTED==checkSelfPermission(perm));
    }

    private boolean canAccessLocation() {
        return(hasPermission(Manifest.permission.ACCESS_FINE_LOCATION) && hasPermission(Manifest.permission.ACCESS_COARSE_LOCATION));
    }

    void markLocation(String label,String lat,String lng){
        LatLng myLatLng = new LatLng(Double.valueOf(lat),Double.valueOf(lng));
        mMap.addMarker(new MarkerOptions().position(myLatLng).title(label+" : "+lat+" , "+lng));
    }

    void fetchPlacesfromDB(){

        LatLng loc = myMarkedLatLng;
        //coordinates of Doordarshan Goa used as an example
        //String jsonString = "[{'lat':'15.489853','lng':'73.824550'}]";
        String jsonString = "[{'lat':'"+loc.latitude+"','lng':'"+loc.longitude+"'}]";
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, Constants.PLACES, jsonArray, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String responsemsg = null;

                System.out.println("Response length===========>"+response.length());
                places = response;
                System.out.println("PLACES :::: "+places);

                for(int i=0;i<places.length();i++)
                {
                    try {
                        JSONObject obj  = places.getJSONObject(i);
                        responsemsg = obj.getString("message");
                        if(responsemsg.equals("Places fetched")){
                            System.out.println("....Place :: "+obj.getString("place"));
                            markLocation(obj.getString("place"),obj.getString("latitude"),obj.getString("longitude"));
                            placeSet.add(obj.getString("place"));
                        }
                        else
                        {
                            System.out.println("Message : " +responsemsg);
                        }

                        //markLocation("My Location","15.489853","73.824550");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                sendNotification(placeSet.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("..........Some error here........"+error);
            }
        });

        request.setRetryPolicy(new DefaultRetryPolicy(10000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    public void sendNotification(String msg){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(GoaMap.this)
                .setSmallIcon(R.drawable.ic_message)
                .setContentTitle("Some more places to visit")
                .setContentText(msg)
                .setAutoCancel(true);
        Intent intent = new Intent(GoaMap.this,NotificationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("message",msg);

        PendingIntent pendingIntent = PendingIntent.getActivity(GoaMap.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,builder.build());
    }

}
