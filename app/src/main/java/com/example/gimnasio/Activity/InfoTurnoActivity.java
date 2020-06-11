package com.example.gimnasio.Activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gimnasio.R;

import androidx.appcompat.app.AppCompatActivity;

public class InfoTurnoActivity extends AppCompatActivity implements View.OnClickListener {

    TextView fechaTurno, hIni, hFin, durac, capacidad, fechaCreac, cantPers;
    ImageView imgIcono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_turno);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        loadViews();

        loadListener();

        loadData();

        setToolbar();

    }

    private void setToolbar() {
        ((TextView) findViewById(R.id.txtTitulo)).setText("Turnos");
    }

    private void loadData() {

    }


    private void loadListener() {
        imgIcono.setOnClickListener(this);

    }

    private void loadViews() {
        imgIcono = findViewById(R.id.imgFlecha);
        fechaTurno = findViewById(R.id.txtFechaT);
        hIni = findViewById(R.id.txtHoraI);
        hFin = findViewById(R.id.txtHoraF);
        durac = findViewById(R.id.txtDuracion);
        capacidad = findViewById(R.id.txtCapacid);
        fechaCreac = findViewById(R.id.txtFechaCrea);
        cantPers = findViewById(R.id.txtCantPers);

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