package com.davronxolboyev.app.shifrlashlar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;


public class MainActivity extends AppCompatActivity {
    
    MaterialButton shifrlash;
    MaterialButton deshifrlash;

    public static int ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shifrlash = findViewById(R.id.shifrBtn);
        deshifrlash = findViewById(R.id.deshifrBtn);

        shifrlash.setOnClickListener(v -> {
            ID = 1;
            startNewIntent();
        });
        deshifrlash.setOnClickListener(v -> {
            ID = 2;
            startNewIntent();
        });
    }

    public void startNewIntent() {
        Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
        startActivity(intent,
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Shifrlashlar")
                .setIcon(R.drawable.ic_baseline_code_24)
                .setMessage("Dasturdan chiqasizmi?")
                .setNegativeButton("Ha", (dialog, which) -> super.onBackPressed())
        .setPositiveButton("Yo`q",((dialog, which) -> dialog.cancel()));
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}