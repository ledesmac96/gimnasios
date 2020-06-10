package com.example.gimnasio.Dialogos;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gimnasio.Fragment.DatePickerFragment;
import com.example.gimnasio.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class NuevoTurnoDialog extends DialogFragment implements View.OnClickListener {

    View view;
    EditText fechaTurno, hIni, hFin, durac, capacidad, dni;
    Button btnGuardar;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.dialog_nuevo_turno, container, false);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Esto es lo nuevoooooooo, evita los bordes cuadrados
        if (getDialog().getWindow() != null)
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        loadViews();

        loadData();

        loadListener();

        return view;
    }

    private void loadData() {

    }


    private void loadListener() {
        btnGuardar.setOnClickListener(this);

    }

    private void loadViews() {
        btnGuardar = view.findViewById(R.id.btnGuardar);
        fechaTurno = view.findViewById(R.id.edtFechaT);
        hIni = view.findViewById(R.id.edtHoraIni);
        hFin = view.findViewById(R.id.edtHoraFin);
        durac = view.findViewById(R.id.edtDurac);
        capacidad = view.findViewById(R.id.edtCapacidad);
        dni = view.findViewById(R.id.edtDni);

    }

    private void selectDate() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String mes, dia;
                month = month + 1;
                if (month < 10) {
                    mes = "0" + month;
                } else
                    mes = String.valueOf(month);
                if (day < 10)
                    dia = "0" + day;
                else
                    dia = String.valueOf(day);
                final String selectedDate = year + "-" + mes + "-" + dia;
                fechaTurno.setText(selectedDate);
            }
        });
        newFragment.show(getFragmentManager(), "datePicker");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnGuardar:
                //guardarTurno
                break;
        }
    }
}
