package com.example.gimnasio.Dialogos;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.ProgressBar;
import android.widget.TimePicker;

import com.example.gimnasio.Fragment.DatePickerFragment;
import com.example.gimnasio.Modelo.Turno;
import com.example.gimnasio.R;
import com.example.gimnasio.Utils.Utils;
import com.example.gimnasio.Utils.Validador;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class NuevoTurnoDialog extends DialogFragment implements View.OnClickListener {

    View view;
    EditText fechaTurno, hIni, hFin, durac, capacidad, dni;
    Button btnGuardar;
    ProgressBar mProgressBar;
    Activity mActivity;
    String tDia, tMes, tAnio;

    @Nullable
    public Activity getSActivity() {
        return mActivity;
    }

    public void setActivity(Activity activity) {
        mActivity = activity;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.dialog_nuevo_turno, container, false);

        //Esto es lo nuevoooooooo, evita los bordes cuadrados
        if (getDialog().getWindow() != null) {
            getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        loadViews();

        loadData();

        loadListener();

        return view;
    }

    private void loadData() {
        mProgressBar.setVisibility(View.GONE);

    }


    private void loadListener() {
        btnGuardar.setOnClickListener(this);
        fechaTurno.setOnClickListener(this);
        hFin.setOnClickListener(this);
        hIni.setOnClickListener(this);

    }

    private void loadViews() {
        mProgressBar = view.findViewById(R.id.progress_bar);
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
                tDia = dia;
                tMes = mes;
                tAnio = String.valueOf(year);
                fechaTurno.setText(selectedDate);
            }
        });
        newFragment.show(getActivity().getSupportFragmentManager(), "dialog");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edtHoraIni:
                showTimer(1);
                break;
            case R.id.edtHoraFin:
                showTimer(2);
                break;
            case R.id.btnGuardar:
                saveTurno();
                break;
            case R.id.edtFechaT:
                selectDate();
                break;
        }
    }

    public void saveTurno() {
        String capac = capacidad.getText().toString().trim();
        String duraci = durac.getText().toString().trim();
        String horaInicio = hIni.getText().toString().trim();
        String horaFin = hFin.getText().toString().trim();
        Validador validador = new Validador(getContext());
        if (validador.validarNumero(capacidad) && validador.validarNumero(duraci)) {
            Turno turno = new Turno(0, 1, horaInicio, horaFin, tDia, tMes, tAnio, Utils.getFecha()
                    , Integer.parseInt(duraci), Integer.parseInt(capac));
            saveTurno(turno);
        } else {
            Utils.showToast(getContext(), getString(R.string.camposIncompletos));
        }
    }

    private void saveTurno(Turno turno) {
        setCancelable(false);
        mProgressBar.setVisibility(View.VISIBLE);
        btnGuardar.setVisibility(View.GONE);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("turnos").add(turno)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Utils.showToast(getContext(), "¡Error al registrarse!");
                        mProgressBar.setVisibility(View.GONE);
                        btnGuardar.setVisibility(View.VISIBLE);
                    }
                }).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Utils.showToast(getContext(), "¡Turno creado!");
                dismiss();
            }
        });

    }

    private void showTimer(final int i) {
        Calendar time = Calendar.getInstance();
        final int hora = time.get(Calendar.HOUR_OF_DAY);
        int minuto = time.get(Calendar.MINUTE);
        TimePickerDialog pickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String horaT, minutoT;
                if (hourOfDay < 10) {
                    horaT = "0" + hourOfDay;
                } else
                    horaT = String.valueOf(hourOfDay);
                if (minute < 10)
                    minutoT = "0" + minute;
                else
                    minutoT = String.valueOf(minute);
                if (i == 1) hIni.setText(String.format("%s:%s", horaT, minutoT));
                else hFin.setText(String.format("%s:%s", horaT, minutoT));
            }
        }, hora, minuto, true);
        pickerDialog.setTitle(i == 1 ? "Elije la hora de inicio" : "Elige una hora de fin");
        pickerDialog.show();
    }
}
