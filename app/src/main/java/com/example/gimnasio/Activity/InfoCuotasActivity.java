package com.example.gimnasio.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gimnasio.Adapters.CuotasAdapter;
import com.example.gimnasio.Modelo.Cuota;
import com.example.gimnasio.Modelo.Usuario;
import com.example.gimnasio.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InfoCuotasActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtTotal;
    ImageView imgIcono;
    ArrayList<Cuota> mCuotas;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView recyclerCuotas;
    CuotasAdapter mCuotasAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_cuotas);

        loadViews();

        loadListener();

        loadData();

        setToolbar();

    }

    private void setToolbar() {
        ((TextView) findViewById(R.id.txtTitulo)).setText("Info cuotas");
    }

    private void loadData() {
        mCuotas = new ArrayList<>();

        mCuotasAdapter = new CuotasAdapter(mCuotas, getApplicationContext());
        mLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerCuotas.setNestedScrollingEnabled(true);
        recyclerCuotas.setLayoutManager(mLayoutManager);
        recyclerCuotas.setAdapter(mCuotasAdapter);

//        ItemClickSupport itemClickSupport = ItemClickSupport.addTo(recyclerCuotas);
//        itemClickSupport.setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
//            @Override
//            public void onItemClick(RecyclerView parent, View view, int position, long id) {
//                Intent i = new Intent(getApplicationContext(), InfoUsuarioActivity.class);
//                i.putExtra(Utils.CUOTA, mCuotas.get(position));
//                startActivity(i);
//            }
//        });
    }


    private void loadListener() {
        imgIcono.setOnClickListener(this);

    }

    private void loadViews() {
        recyclerCuotas = findViewById(R.id.recycler);
        imgIcono = findViewById(R.id.imgFlecha);
        txtTotal = findViewById(R.id.txtTotal);

    }

    private void getCuotas() { //modificar dirección
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("turnos").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot query : task.getResult()) {
                        Cuota cuota = Cuota.toMap(query.getData());
                        if (cuota != null) {
                            mCuotas.add(cuota);
                        }
                    }
                    if (mCuotasAdapter != null)
                        mCuotasAdapter.change(mCuotas);
                }

            }
        });
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
