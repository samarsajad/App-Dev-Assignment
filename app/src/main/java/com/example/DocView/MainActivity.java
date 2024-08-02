package com.example.DocView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.statusBarColor));

        Button buttonSender = findViewById(R.id.button_sender);
        Button buttonReceiver = findViewById(R.id.button_receiver);

        buttonSender.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SenderLoginActivity.class)));
        buttonReceiver.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ReceiverLoginActivity.class)));
    }
}