package com.example.gimnasio.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.gimnasio.Activity.AgregarUsuarioActivity;
import com.example.gimnasio.Activity.ConfirmUserActivity;
import com.example.gimnasio.Activity.InfoUsuarioActivity;
import com.example.gimnasio.Adapters.UsuariosAdapter;
import com.example.gimnasio.Modelo.Turno;
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

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class GestionUsuariosFragment extends Fragment implements View.OnClickListener {

    View view;
    CardView cardAddClient, cardConfirmar;
    ArrayList<Usuario> mUsuarios;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView recyclerUsuarios;
    UsuariosAdapter mUsuariosAdapter;
    FragmentManager mFragmentManager;
    ProgressBar mProgressBar;
    Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Crea la vista de Inicio
        view = inflater.inflate(R.layout.fragment_gestion_usuarios, container, false);

        loadViews();

        loadData();

        loadDatos();

        //getUsuarios(); HAY UN ERROR CON EL MODELO USUARIO

        return view;
    }

    private void loadDatos() {

    }

    private void loadViews() {
        recyclerUsuarios = view.findViewById(R.id.recycler);
        cardAddClient = view.findViewById(R.id.cardAddCliente);
        cardConfirmar = view.findViewById(R.id.cardConfirm);
        mProgressBar = view.findViewById(R.id.progress_bar);
    }

    private void loadData() {
        mUsuarios = new ArrayList<>();

        mProgressBar.setVisibility(View.VISIBLE);
        mUsuariosAdapter = new UsuariosAdapter(mUsuarios, getContext());
        mLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerUsuarios.setNestedScrollingEnabled(true);
        recyclerUsuarios.setLayoutManager(mLayoutManager);
        recyclerUsuarios.setAdapter(mUsuariosAdapter);

        ItemClickSupport itemClickSupport = ItemClickSupport.addTo(recyclerUsuarios);
        itemClickSupport.setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position, long id) {
                Intent i = new Intent(getContext(), InfoUsuarioActivity.class);
                i.putExtra(Utils.USUARIOS, mUsuarios.get(position));
                startActivity(i);
            }
        });

        cardAddClient.setOnClickListener(this);
        cardConfirmar.setOnClickListener(this);

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
                    if (mUsuariosAdapter != null)
                        mUsuariosAdapter.change(mUsuarios);
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
            case R.id.cardAddCliente:
                Intent i = new Intent(getContext(), AgregarUsuarioActivity.class);
                startActivity(i);
                break;
            case R.id.cardConfirm:
                Toast.makeText(mContext, "PRONTO WE", Toast.LENGTH_SHORT).show();
//                Intent j = new Intent(getContext(), ConfirmUserActivity.class);
//                startActivity(j);
                break;
        }
    }
}
