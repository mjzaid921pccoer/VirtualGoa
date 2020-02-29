package com.example.vgoa;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ImageButton nPlacesBtn;
    String currentImagePath =null;
    private static final int IMAGE_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView  (R.layout.activity_main);

        nPlacesBtn = findViewById(R.id.places_btn);

    }

    //Review module
    public void goToReview(View view){
        Intent intent = new Intent(MainActivity.this,Review.class);
        startActivity(intent);
    }

    //Camera module
    public void captureImage(View view) throws IOException {

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(cameraIntent.resolveActivity(getPackageManager())!=null)
        {
            File imageFile = null;
            imageFile = getImageFile();

            if(imageFile!=null)
            {
                Uri imageUri = FileProvider.getUriForFile(this,"com.vgoa.data",imageFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(cameraIntent,IMAGE_REQUEST);
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        System.out.println("\n\nRequest code :"+requestCode+"\t"+resultCode);
        System.out.println("\n\nData:"+data);
        if(requestCode == IMAGE_REQUEST){
            uploadImageToDB();
        }
    }

    private File getImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageName ="jpg_"+timeStamp+"_";
        File storageDir =getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File imageFile = File.createTempFile(imageName,".jpg",storageDir);
        currentImagePath =imageFile.getAbsolutePath();
        return imageFile;
    }

    public void displayimage(View view)
    {
        Intent intent = new Intent(this,MonumentInfo.class);
        intent.putExtra("image_path",currentImagePath);
        startActivity(intent);
    }


    //Google maps
    public void goToMaps(View view ){

        Intent intent = new Intent(this,GoaMap.class);
        startActivity(intent);

    }

    //Navigate to 360 Activity
    public void goTo360Activity(View view){
        Intent intent = new Intent(MainActivity.this,Activity360.class);
        startActivity(intent);
    }

    //
    public void goToSearch_Place(View view){
        Intent intent = new Intent(MainActivity.this,Search_Place.class);
        startActivity(intent);
    }


    public void uploadImageToDB(){

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                Bitmap bitmap= BitmapFactory.decodeFile(currentImagePath);

                if(bitmap!=null){
                    System.out.println("......Image found......\n\n");
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
                    byte [] imageBytes = baos.toByteArray();
                    final String imageString = Base64.encodeToString(imageBytes,Base64.DEFAULT);
                    //System.out.println("........ImageString........"+imageString);

                    System.out.println("\n\n....................Started uploading image....\n");
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.IMAGE_UPLOAD_URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            System.out.println("\n\nI got this response:"+response);
                            if(response.equals("1")){
                                System.out.println("Image uploaded to server:"+response+" : "+response.length());
                                Toast.makeText(MainActivity.this, "Image uploaded to server", Toast.LENGTH_SHORT).show();
                            }
                            else if(response.equals("0")){
                                System.out.println("Couldn't upload image: "+response+" : "+response.length());
                                Toast.makeText(MainActivity.this, "Couldn't upload image", Toast.LENGTH_SHORT).show();
                            }
                            System.out.println("............I am here..............");
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            System.out.println(".......errror........"+error);
                            Toast.makeText(MainActivity.this, "Some error occured:"+error, Toast.LENGTH_SHORT).show();
                        }
                    }){
                        //adding parameters to send
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> parameters = new HashMap<>();
                            parameters.put("uploaded_file", imageString);
                            parameters.put("name","myimage12");
                            return parameters;
                        }
                    };

                    RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                    queue.add(stringRequest);

                }
                else System.out.println("...........No image found........\n\n");
                //System.out.println("\n\n......................Image uploaded...................");

            }
        });
        t.start();

    }

    public void gotToGoaWebView(View view){
        Intent i = new Intent(MainActivity.this,GoaWeb.class);
        startActivity(i);
    }

}
