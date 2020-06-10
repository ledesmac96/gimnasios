package com.example.gimnasio.Activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.TextView;

import com.example.gimnasio.R;

import androidx.appcompat.app.AppCompatActivity;

public class InfoTurnoActivity extends AppCompatActivity {

    TextView fechaTurno, hIni, hFin, durac, capacidad, fechaCreac, cantPers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_turno);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

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
        fechaTurno = findViewById(R.id.txtFechaT);
        hIni = findViewById(R.id.txtHoraI);
        hFin = findViewById(R.id.txtHoraF);
        durac = findViewById(R.id.txtDuracion);
        capacidad = findViewById(R.id.txtCapacid);
        fechaCreac = findViewById(R.id.txtFechaCrea);
        cantPers = findViewById(R.id.txtCantPers);

    }

}