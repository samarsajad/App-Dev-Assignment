package com.example.DocView;
//import android.content.SharedPreferences;
//import android.graphics.Bitmap;
//import android.net.Uri;
//import android.os.Bundle;
//import android.provider.MediaStore;
//import android.util.Log;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import androidx.appcompat.app.AppCompatActivity;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.Set;
//
//public class ViewImagesActivity extends AppCompatActivity {
//    private static final String TAG = "ViewImagesActivity";
//
//    private LinearLayout layoutImages;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_view_images);
//        Log.d(TAG, "ViewImagesActivity onCreate called");
//
//        layoutImages = findViewById(R.id.layoutImages);
//        loadImages();
//    }
//
//    private void loadImages() {
//        ArrayList<String> imageUris = getImageUris();
//
//        for (String uriString : imageUris) {
//            Uri uri = Uri.parse(uriString);
//            try {
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
//                ImageView imageView = new ImageView(this);
//                imageView.setImageBitmap(bitmap);
//                layoutImages.addView(imageView);
//            } catch (IOException e) {
//                Log.e(TAG, "Error loading image", e);
//            }
//        }
//    }
//
//    private ArrayList<String> getImageUris() {
//        Set<String> uriSet = getSharedPreferences("ImageURIs", MODE_PRIVATE).getStringSet("uris", new HashSet<>());
//        return new ArrayList<>(uriSet);
//    }
//
//}


import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.R;

public class ViewImagesActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_images);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.statusBarColor));

        imageView = findViewById(R.id.imageView);

        // Display the first image from the uploaded images list
        if (!UploadImageActivity.uploadedImages.isEmpty()) {
            Bitmap firstImage = UploadImageActivity.uploadedImages.get(0);
            imageView.setImageBitmap(firstImage);
        }
    }
}

