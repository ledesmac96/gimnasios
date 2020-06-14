package com.example.gimnasio.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gimnasio.Adapters.CuotasAdapter;
import com.example.gimnasio.Adapters.UsuariosAdapter;
import com.example.gimnasio.Modelo.Cuota;
import com.example.gimnasio.Modelo.Usuario;
import com.example.gimnasio.R;
import com.example.gimnasio.RecyclerListener.ItemClickSupport;
import com.example.gimnasio.Utils.Utils;
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

public class InfoUsuarioActivity extends AppCompatActivity implements View.OnClickListener {

    TextView dni, nameAp, fechaRegistro, telefono, mail, estado, message;
    ImageView imgIcono;
    Usuario mUsuario;
    Button btnHab, btnDeshab;
    ArrayList<Cuota> mCuotas;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView recyclerCuotas;
    CuotasAdapter mCuotasAdapter;
    ProgressBar mProgressBar;
    LinearLayout linlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_usuario);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        if (getIntent().getParcelableExtra(Utils.USUARIOS) != null) {
            mUsuario = getIntent().getParcelableExtra(Utils.USUARIOS);
        }

        loadViews();

        loadListener();

        loadData();

        setToolbar();

    }

    private void setToolbar() {
        ((TextView) findViewById(R.id.txtTitulo)).setText("Info usuarios");
    }

    private void loadData() {
        dni.setText(String.valueOf(mUsuario.getDni()));
        String nomap = mUsuario.getNombre() + " " + mUsuario.getApellido();
        nameAp.setText(nomap);
        telefono.setText(mUsuario.getTelefono());
        mail.setText(mUsuario.getMail());
        fechaRegistro.setText(mUsuario.getFechaRegistro());
        estado.setText(String.valueOf(mUsuario.getEstado()));

        mCuotas = new ArrayList<>();

        mProgressBar.setVisibility(View.VISIBLE);
        mCuotasAdapter = new CuotasAdapter(mCuotas, getApplicationContext());
        mLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerCuotas.setNestedScrollingEnabled(true);
        recyclerCuotas.setLayoutManager(mLayoutManager);
        recyclerCuotas.setAdapter(mCuotasAdapter);

        if(mCuotas.size() == 0){
            linlay.setVisibility(View.GONE);
            message.setVisibility(View.VISIBLE);
        }

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
        btnHab.setOnClickListener(this);
        btnDeshab.setOnClickListener(this);

    }

    private void loadViews() {
        recyclerCuotas = findViewById(R.id.recyclerCuotas);
        imgIcono = findViewById(R.id.imgFlecha);
        dni = findViewById(R.id.txtDNI);
        nameAp = findViewById(R.id.txtNameAp);
        telefono = findViewById(R.id.txtTel);
        mail = findViewById(R.id.txtMail);
        estado = findViewById(R.id.txtEstado);
        fechaRegistro = findViewById(R.id.txtFechaReg);

        btnDeshab = findViewById(R.id.btnDesh);
        btnHab = findViewById(R.id.btnHabi);

        mProgressBar = findViewById(R.id.progress_bar);
        message = findViewById(R.id.txtMessage);
        linlay = findViewById(R.id.linlay);
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
                    if (mProgressBar != null) mProgressBar.setVisibility(View.GONE);
                    if (mCuotasAdapter != null)
                        mCuotasAdapter.change(mCuotas);
                }

            }
        });
    }


    @Override
    public void onClick(View v) {
        int dni = Integer.parseInt(String.valueOf(mUsuario.getDni()));
        switch (v.getId()){
            case R.id.imgFlecha:
                onBackPressed();
                break;
            case R.id.btnDesh:
                setValue(0, dni);
                break;
            case R.id.btnHabi:
                setValue(1, dni);
                break;
        }
    }

    private void setValue(final int i, int dni) {
        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        final CollectionReference usuarios = rootRef.collection("usuarios");
        usuarios.whereEqualTo("dni", dni).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Map<Object, String> map = new HashMap<>();
                        map.put("estado", Integer.toString(i));
                        usuarios.document(document.getId()).set(map, SetOptions.merge());
                    }
                    if(i == 0)
                        Toast.makeText(InfoUsuarioActivity.this, "Usuario deshabilitado", Toast.LENGTH_SHORT).show();
                    Toast.makeText(InfoUsuarioActivity.this, "Usuario habilitado", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

}
