package com.example.gimnasio.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.gimnasio.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loadViews();

        loadListener();

        loadData();

    }

    private void loadData() {

    }


    private void loadListener() {
        //btnBack.setOnClickListener(this);

    }

    private void loadViews() {
        //mInicio = findViewById(R.id.sesionOn);

    }


}
