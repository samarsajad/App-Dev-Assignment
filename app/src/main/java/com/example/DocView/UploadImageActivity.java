package com.example.DocView;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.graphics.Bitmap;
//import android.net.Uri;
//import android.os.Bundle;
//import android.provider.MediaStore;
//import android.util.Log;
//import android.widget.Button;
//import android.widget.ImageView;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.Set;
//import android.widget.Toast;
//
//public class UploadImageActivity extends AppCompatActivity {
//
//    private static final int PICK_IMAGE_REQUEST = 1;
//    private static final String TAG = "UploadImageActivity";
//    private ImageView imageView;
//    private ArrayList<String> imageUris;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_upload_image);
//
//        imageView = findViewById(R.id.imageView);
//        Button buttonChooseImage = findViewById(R.id.buttonChooseImage);
//        Button buttonUploadImage = findViewById(R.id.buttonUploadImage);
//
//        imageUris = getImageUris();
//
//        buttonChooseImage.setOnClickListener(v -> openFileChooser());
//
//        buttonUploadImage.setOnClickListener(v -> {
//            if (imageView.getDrawable() != null) {
//                saveImageUris(imageUris);
//                Toast.makeText(this, "Image uploaded successfully", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(this, "No image selected", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    private void openFileChooser() {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(intent, PICK_IMAGE_REQUEST);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
//            Uri imageUri = data.getData();
//            try {
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
//                imageView.setImageBitmap(bitmap);
//                imageUris.add(imageUri.toString());
//            } catch (IOException e) {
//                Log.e(TAG, "Error loading image", e);
//            }
//        }
//    }
//
//    private void saveImageUris(ArrayList<String> imageUris) {
//        getSharedPreferences("ImageURIs", MODE_PRIVATE)
//                .edit()
//                .putStringSet("uris", new HashSet<>(imageUris))
//                .apply();
//    }
//
//    private ArrayList<String> getImageUris() {
//        Set<String> uriSet = getSharedPreferences("ImageURIs", MODE_PRIVATE).getStringSet("uris", new HashSet<>());
//        ArrayList<String> uriList = new ArrayList<>(uriSet);
//        Log.d(TAG, "Image URIs: " + uriList.toString()); // Log URIs to verify
//        return uriList;
//    }
//
//}


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.R;

import java.io.IOException;
import java.util.ArrayList;

public class UploadImageActivity extends AppCompatActivity {

    // Static list to store uploaded images
    public static ArrayList<Bitmap> uploadedImages = new ArrayList<>();
    private ImageView imageView;
    private Button buttonChooseImage, buttonUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.statusBarColor));

        imageView = findViewById(R.id.imageView);
        buttonChooseImage = findViewById(R.id.buttonChooseImage);
        buttonUpload = findViewById(R.id.buttonUploadImage);

        buttonChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        });

        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Notify the user
                Toast.makeText(UploadImageActivity.this, "Image uploaded successfully", Toast.LENGTH_SHORT).show();

                // Open ViewImagesActivity
                Intent intent = new Intent(UploadImageActivity.this, ViewImagesActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imageView.setImageBitmap(bitmap);
                uploadedImages.add(bitmap); // Add image to the list
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

