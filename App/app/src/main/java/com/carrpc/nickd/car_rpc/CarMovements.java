package com.carrpc.nickd.car_rpc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.widget.TextView;


public class CarMovements extends AppCompatActivity {
        ImageButton left = (ImageButton) findViewById(R.id.leftArr);
        ImageButton right = (ImageButton) findViewById(R.id.rightArr);
        ImageButton up = (ImageButton) findViewById(R.id.upArr);
        ImageButton down = (ImageButton) findViewById(R.id.downArr);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_car_movements);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
