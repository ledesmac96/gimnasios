package com.example.gimnasio.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.gimnasio.Activity.InfoCuotasActivity;
import com.example.gimnasio.Dialogos.NuevaCuotaDialog;
import com.example.gimnasio.Modelo.Turno;
import com.example.gimnasio.R;
import com.example.gimnasio.Utils.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class GestionCuotasFragment extends Fragment implements View.OnClickListener {

    View view;
    FloatingActionButton btnAdd;
    Calendar calendar;
    CalendarView calendarView;
    ProgressBar progres;
    FragmentManager mFragmentManager;
    Button btnAgregar;
    Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Crea la vista de Inicio
        view = inflater.inflate(R.layout.fragment_gestion_cuotas, container, false);

        loadView();

        loadData();

        loadListener();

        return view;
    }

    private void loadView() {
        calendarView = view.findViewById(R.id.calendarView);
        progres = view.findViewById(R.id.progress_bar);
        btnAgregar = view.findViewById(R.id.btnCuota);
    }

    private void loadData() {
        calendar = Calendar.getInstance();

//        calendar.set(Calendar.MONTH, Calendar.NOVEMBER);
//        calendar.set(Calendar.DAY_OF_MONTH, 9);
//        calendar.set(Calendar.YEAR, 2012);
//
//        calendar.add(Calendar.YEAR, 1);
//        calendar.add(Calendar.DAY_OF_MONTH, 1);

    }

    private void loadListener() {
        btnAgregar.setOnClickListener(this);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                Intent h = new Intent(getContext(), InfoCuotasActivity.class);
                startActivity(h);

            }
        });
    }

    private void getTurnos() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("usuarios").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot query : task.getResult()){
                        Turno turno = query.toObject(Turno.class);
                        if (turno != null){
                            //mTurnos.add(turno);
                        }
                    }
                }

            }
        });
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.mFragmentManager = fragmentManager;
    }

    public FragmentManager getManagerFragment() {
        return this.mFragmentManager;
    }

    @Override
    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCuota:
                NuevaCuotaDialog nuevaCuotaDialog = new NuevaCuotaDialog();
                nuevaCuotaDialog.setActivity(getActivity());
                nuevaCuotaDialog.show(getManagerFragment(), "turno");
                break;
        }
    }
}
