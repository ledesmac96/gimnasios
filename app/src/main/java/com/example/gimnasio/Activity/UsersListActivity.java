package com.example.gimnasio.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.gimnasio.Adapters.UsuariosAdapter;
import com.example.gimnasio.Modelo.Usuario;
import com.example.gimnasio.R;
import com.example.gimnasio.RecyclerListener.ItemClickSupport;
import com.example.gimnasio.Utils.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsersListActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView imgIcono;
    CardView cardAddClient;
    ArrayList<Usuario> mUsuarios;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView recyclerUsuarios;
    UsuariosAdapter mUsuariosAdapter;
    ProgressBar mProgressBar;
    EditText edtBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);

        loadViews();

        loadListener();

        loadData();

        setToolbar();
    }

    private void setToolbar() {
        ((TextView) findViewById(R.id.txtTitulo)).setText("Turnos");
    }

    private void loadData() {
        mUsuarios = new ArrayList<>();

        mProgressBar.setVisibility(View.VISIBLE);
        mUsuariosAdapter = new UsuariosAdapter(mUsuarios, getApplicationContext());
        mLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerUsuarios.setNestedScrollingEnabled(true);
        recyclerUsuarios.setLayoutManager(mLayoutManager);
        recyclerUsuarios.setAdapter(mUsuariosAdapter);

//        ItemClickSupport itemClickSupport = ItemClickSupport.addTo(recyclerUsuarios);
////        itemClickSupport.setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
////            @Override
////            public void onItemClick(RecyclerView parent, View view, int position, long id) {
////                Intent i = new Intent(getApplicationContext(), InfoUsuarioActivity.class);
////                i.putExtra(Utils.USUARIOS, mUsuarios.get(position));
////                startActivity(i);
////            }
////        });

        getUsuarios();

    }


    private void loadListener() {
        imgIcono.setOnClickListener(this);
        cardAddClient.setOnClickListener(this);
        edtBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                buscar(s.toString());
            }
        });

    }

    private void loadViews() {
        imgIcono = findViewById(R.id.imgFlecha);
        recyclerUsuarios = findViewById(R.id.recycler);
        cardAddClient = findViewById(R.id.cardAdd);
        mProgressBar = findViewById(R.id.progress_bar);
        edtBuscar = findViewById(R.id.edtBuscar);

    }

    private void buscar(String txt) {
        Pattern pattern;
        pattern = Pattern.compile("([0-9]+){1,8}");
        Matcher matcher = pattern.matcher(txt);
        if (matcher.find()) {
            mUsuariosAdapter.filtrar(txt, Utils.LIST_DNI);
            return;
        }else{
            pattern = Pattern.compile("[a-zA-Z_ ]+");
            matcher = pattern.matcher(txt);
            if (matcher.find()) {
                mUsuariosAdapter.filtrar(txt, Utils.LIST_NOMBRE);
                return;
            } else {
                mUsuariosAdapter.filtrar(txt, Utils.LIST_RESET);
                return;
            }
        }

    }

    private void getUsuarios() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("usuarios").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot query : task.getResult()) {
                        Usuario usuario = Usuario.toMap(query.getData());
                        if (usuario != null) {
                            mUsuarios.add(usuario);
                        }
                    }
                    if (mProgressBar != null) mProgressBar.setVisibility(View.GONE);
                    if (mUsuariosAdapter != null) {
                        sort();
                        mUsuariosAdapter.change(mUsuarios);

                    }
                }

            }
        });
    }

    private void sort() {
        Comparator<Usuario> comparator = new Comparator<Usuario>() {
            @Override
            public int compare(Usuario o1, Usuario o2) {
                return String.valueOf(o1.getEstado()).compareTo(String.valueOf(o2.getEstado()));
            }
        };
        Collections.sort(mUsuarios, comparator);
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
