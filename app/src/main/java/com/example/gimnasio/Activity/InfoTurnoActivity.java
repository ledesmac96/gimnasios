package com.example.gimnasio.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gimnasio.Modelo.Turno;
import com.example.gimnasio.R;
import com.example.gimnasio.Utils.Utils;

import androidx.appcompat.app.AppCompatActivity;

public class InfoTurnoActivity extends AppCompatActivity implements View.OnClickListener {

    TextView fechaTurno, hIni, hFin, durac, capacidad, fechaCreac, cantPers;
    ImageView imgIcono;
    Turno mTurno;
    int cantPersonas = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_turno);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        if (getIntent().getParcelableExtra(Utils.TURNOS) != null) {
            mTurno = getIntent().getParcelableExtra(Utils.TURNOS);
        }

        Intent intent = getIntent();
        cantPersonas = intent.getIntExtra("cant", 0);

        loadViews();

        loadListener();

        loadData();

        setToolbar();

    }

    private void setToolbar() {
        ((TextView) findViewById(R.id.txtTitulo)).setText("Turnos");
    }

    private void loadData() {
        String fecha = mTurno.getDia() + "/" + mTurno.getMes() + "/" + mTurno.getAnio();
        fechaTurno.setText(fecha);
        hIni.setText(mTurno.getHoraInicio());
        hFin.setText(mTurno.getHoraFin());
        durac.setText(String.valueOf(mTurno.getDuracionMinutos()));
        cantPers.setText(String.valueOf(mTurno.getCapacidad()));
        fechaCreac.setText(mTurno.getFechaCreacion());
        cantPers.setText(String.valueOf(cantPersonas));
    }


    private void loadListener() {
        imgIcono.setOnClickListener(this);

    }

    private void loadViews() {
        imgIcono = findViewById(R.id.imgFlecha);
        fechaTurno = findViewById(R.id.txtFechaT);
        hIni = findViewById(R.id.txtHoraInicio);
        hFin = findViewById(R.id.txtHoraFin);
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