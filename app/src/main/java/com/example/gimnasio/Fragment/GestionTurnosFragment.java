package com.example.gimnasio.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.gimnasio.Activity.InfoTurnoActivity;
import com.example.gimnasio.Adapters.TurnosAdapter;
import com.example.gimnasio.Dialogos.NuevoTurnoDialog;
import com.example.gimnasio.Modelo.Turno;
import com.example.gimnasio.R;
import com.example.gimnasio.RecyclerListener.ItemClickSupport;
import com.example.gimnasio.Utils.Utilssss;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class GestionTurnosFragment extends Fragment {

    View view;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView recyclerTurnos;
    ArrayList<Turno> mTurnos;
    TurnosAdapter mTurnosAdapter;
    FragmentManager mFragmentManager;
    LinearLayout layoutFondo;
    Context mContext;
    Button btnTurno;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Crea la vista de Inicio
        view = inflater.inflate(R.layout.fragment_gestion_turnos, container, false);

        loadViews();

        loadData();

        return view;
    }


    private void loadViews() {
        recyclerTurnos = view.findViewById(R.id.recycler);
        btnTurno = view.findViewById(R.id.btnTurno);
    }

    private void loadData() {
        mTurnos = new ArrayList<>();

        mTurnosAdapter = new TurnosAdapter(mTurnos, getContext());
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false);
        recyclerTurnos.setNestedScrollingEnabled(true);
        recyclerTurnos.setLayoutManager(mLayoutManager);
        recyclerTurnos.setAdapter(mTurnosAdapter);

        ItemClickSupport itemClickSupport = ItemClickSupport.addTo(recyclerTurnos);
        itemClickSupport.setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position, long id) {
                Intent i = new Intent(getContext(), InfoTurnoActivity.class);
                i.putExtra(Utilssss.TURNOS, mTurnos.get(position)); //poné el tuyo we
                startActivity(i);
            }
        });

        btnTurno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NuevoTurnoDialog nuevoTurnoDialog = new NuevoTurnoDialog();
                nuevoTurnoDialog.show(getManagerFragment(), "turno");
            }
        });
    }

    public void loadInfo() {

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
