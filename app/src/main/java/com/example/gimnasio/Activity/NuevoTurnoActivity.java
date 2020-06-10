package com.example.gimnasio.Activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.example.gimnasio.R;

import androidx.appcompat.app.AppCompatActivity;

public class NuevoTurnoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
}