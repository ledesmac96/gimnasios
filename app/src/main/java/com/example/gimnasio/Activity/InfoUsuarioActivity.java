package com.example.gimnasio.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gimnasio.R;

public class InfoUsuarioActivity extends AppCompatActivity implements View.OnClickListener {

    TextView dni, name;
    ImageView imgIcono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_usuario);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        loadViews();

        loadListener();

        loadData();

        setToolbar();

    }

    private void setToolbar() {
        ((TextView) findViewById(R.id.txtTitulo)).setText("Info usuarios");
    }

    private void loadData() {

    }


    private void loadListener() {
        imgIcono.setOnClickListener(this);

    }

    private void loadViews() {
        imgIcono = findViewById(R.id.imgFlecha);
        dni = findViewById(R.id.txtDni);
        name = findViewById(R.id.txtName);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgFlecha:
                onBackPressed();
                break;
        }
    }

}
