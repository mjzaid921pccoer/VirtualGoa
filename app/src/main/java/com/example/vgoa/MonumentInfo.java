package com.example.vgoa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Telephony;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.tensorflow.lite.DataType;
import org.tensorflow.lite.Interpreter;
import org.tensorflow.lite.support.common.FileUtil;
import org.tensorflow.lite.support.common.TensorProcessor;
import org.tensorflow.lite.support.common.ops.NormalizeOp;
import org.tensorflow.lite.support.image.ImageProcessor;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.image.ops.ResizeOp;
import org.tensorflow.lite.support.label.TensorLabel;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.MappedByteBuffer;
import java.util.List;
import java.util.Map;

public class MonumentInfo extends AppCompatActivity {

    ImageView imageView;
    String placeinfo;
    TextView infotext,loc_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monument_info);
        infotext = findViewById(R.id.info_tv);
        loc_name = findViewById(R.id.loc_name);
        placeinfo = getIntent().getStringExtra("place");
        System.out.println("....place name......"+placeinfo);

        System.out.println(".me ithe ahe............");
        imageView=(ImageView)findViewById(R.id.imageView);
        getImage();
    }

    public void goToRating(View view){
        Intent intent =new Intent(MonumentInfo.this,Rating.class);
        startActivity(intent);
    }

    public void goToStatistics(View view){
        Intent intent =new Intent(MonumentInfo.this,ChartActivity.class);
        startActivity(intent);
    }
    void getImage(){
        System.out.print("......inside getImage..........");
        JSONObject obj = new JSONObject();
        try {
            obj.put("placename",placeinfo);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,Constants.IMAGES_URL, obj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("..........I got response..........");
                //Toast.makeText(MainActivity.this, "result:" + response, Toast.LENGTH_LONG).show();

                Bitmap bitmap = null;
                try {
                    loc_name.setText(response.getString("location_name"));
                    infotext.setText(response.getString("info"));
                    new DownLoadImageTask(imageView).execute(response.getString("url"));
                    /*if (bitmap != null) {
                        System.out.println("..............u r in bitmap..........");

                    } else {
                        System.out.println("....................sorry..............");
                    }*/
                    // imageView.setImageBitmap(bitmap);
                    //URL url = new URL(response.getString("url"));

                } catch (Exception e) {

                    e.printStackTrace();
                    System.out.println("......errorrrrrrrrrrr.......");
                }

                //imageView.setImageBitmap();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(".......voleyyyyyyyyyyy....."+error);
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);

    }

    private class DownLoadImageTask extends AsyncTask<String,Void,Bitmap> {
        ImageView imageView;
        public DownLoadImageTask(ImageView imageview){
            this.imageView = imageview;
        }
        protected Bitmap doInBackground(String...urls){
            String urlOfImage = urls[0];
            Bitmap logo=null;
            try {
                InputStream is = new  java.net.URL(urlOfImage).openStream();
                logo =BitmapFactory.decodeStream(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return logo;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //loading = ProgressDialog.show(MainActivity.this,"Downloading Image...","Please wait...",true,true);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            //loading.dismiss();
            imageView.setImageBitmap(bitmap);
        }
    }
}
