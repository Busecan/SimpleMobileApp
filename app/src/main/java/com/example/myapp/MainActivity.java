package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button convertorActivity = findViewById(R.id.convertor_btn);
        Button randomActivity = findViewById(R.id.random_btn);
        Button smsActivity = findViewById(R.id.sms_btn);

        convertorActivity.setOnClickListener(new View.OnClickListener(){
            @Override
                    public void onClick(View view){
                startActivity(new Intent(MainActivity.this,convertorActivity.class));
            }
        });

        randomActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(MainActivity.this,randomActivity.class));
            }
        });

        smsActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(MainActivity.this,smsActivity.class));
            }
        });
    }
}