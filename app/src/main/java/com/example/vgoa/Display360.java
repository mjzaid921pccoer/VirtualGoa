package com.example.vgoa;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;
import java.io.InputStream;

public class Display360 extends AppCompatActivity {

    private VrPanoramaView vrPanoramaView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display360);

        vrPanoramaView = findViewById(R.id.vrPanoramaView);
        Intent intent = getIntent();
        String place = intent.getStringExtra("place");
        loadPhotoSphere(place);

    }

    @Override
    protected void onPause() {
        super.onPause();
        vrPanoramaView.pauseRendering();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        vrPanoramaView.resumeRendering();
    }

    @Override
    protected void onDestroy() {
        vrPanoramaView.shutdown();
        super.onDestroy();
    }

    //method to load the 360 degree view
    private void loadPhotoSphere(String place) {
        if(place.equals("baga")){
            Intent i = new Intent(Display360.this,GoaWeb.class);
            startActivity(i);
        }
        else{
            VrPanoramaView.Options options = new VrPanoramaView.Options();
            InputStream inputStream;
            AssetManager assetManager = getAssets();
            System.out.println("Place name : "+place);
            try {
                inputStream = assetManager.open(place+".jpg");
                options.inputType = VrPanoramaView.Options.TYPE_MONO;
                vrPanoramaView.loadImageFromBitmap(BitmapFactory.decodeStream(inputStream), options);
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

}
