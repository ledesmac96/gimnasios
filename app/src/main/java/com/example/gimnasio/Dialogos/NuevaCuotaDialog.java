package com.example.gimnasio.Dialogos;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.gimnasio.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class NuevaCuotaDialog extends DialogFragment implements View.OnClickListener {

    View view;
    EditText fechaCuota, dni;
    Button btnGuardar;
    ProgressBar mProgressBar;
    Activity mActivity;

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

        view = inflater.inflate(R.layout.dialog_nueva_cuota, container, false);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
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
        String fecha = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
        fechaCuota.setText(fecha);

    }


    private void loadListener() {
        btnGuardar.setOnClickListener(this);

    }

    private void loadViews() {
        mProgressBar = view.findViewById(R.id.progress_bar);
        btnGuardar = view.findViewById(R.id.btnGuardar);
        fechaCuota = view.findViewById(R.id.edtFechaPago);
        dni = view.findViewById(R.id.edtDni);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGuardar:
                //A Firestore, plis
                break;
        }
    }



}