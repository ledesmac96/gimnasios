package com.example.gimnasio.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.gimnasio.Activity.InfoTurnoActivity;
import com.example.gimnasio.Adapters.TurnosAdapter;
import com.example.gimnasio.Dialogos.NuevoTurnoDialog;
import com.example.gimnasio.Modelo.Turno;
import com.example.gimnasio.R;
import com.example.gimnasio.RecyclerListener.ItemClickSupport;
import com.example.gimnasio.Utils.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class GestionTurnosFragment extends Fragment {

    View view;
    ArrayList<Turno> mTurnos;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView recyclerTurnos;
    TurnosAdapter mTurnosAdapter;
    FragmentManager mFragmentManager;
    ProgressBar mProgressBar;
    Context mContext;
    Button btnTurno;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Crea la vista de Inicio
        view = inflater.inflate(R.layout.fragment_gestion_turnos, container, false);

        loadViews();

        loadData();

        loadDatos();

        getTurnos();

        return view;
    }

    private void loadDatos() {
        mTurnos = new ArrayList<>();
    }

    private void getTurnos() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("turnos").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot query : task.getResult()) {
                        Turno turno = Turno.toMap(query.getData());
                        if (turno != null) {
                            mTurnos.add(turno);
                        }
                    }
                    if (mProgressBar != null) mProgressBar.setVisibility(View.GONE);
                    if (mTurnosAdapter != null)
                        mTurnosAdapter.change(mTurnos);
                }

            }
        });
    }


    private void loadViews() {
        mProgressBar = view.findViewById(R.id.progress_bar);
        recyclerTurnos = view.findViewById(R.id.recycler);
        btnTurno = view.findViewById(R.id.btnTurno);
    }

    private void loadData() {
        mTurnos = new ArrayList<>();
        mProgressBar.setVisibility(View.VISIBLE);
        mTurnosAdapter = new TurnosAdapter(mTurnos, getContext());
        mLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerTurnos.setNestedScrollingEnabled(true);
        recyclerTurnos.setLayoutManager(mLayoutManager);
        recyclerTurnos.setAdapter(mTurnosAdapter);

        ItemClickSupport itemClickSupport = ItemClickSupport.addTo(recyclerTurnos);
        itemClickSupport.setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position, long id) {
                Intent i = new Intent(getContext(), InfoTurnoActivity.class);
                i.putExtra(Utils.TURNOS, mTurnos.get(position)); //poné el tuyo we
                startActivity(i);
            }
        });

        btnTurno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NuevoTurnoDialog nuevoTurnoDialog = new NuevoTurnoDialog();
                nuevoTurnoDialog.setActivity(getActivity());
                nuevoTurnoDialog.show(getManagerFragment(), "turno");
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


}
