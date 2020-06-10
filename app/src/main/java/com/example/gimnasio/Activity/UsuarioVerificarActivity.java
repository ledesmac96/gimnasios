package com.example.gimnasio.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gimnasio.Modelo.Usuario;
import com.example.gimnasio.R;
import com.example.gimnasio.Utils.PreferenciasManager;
import com.example.gimnasio.Utils.Utils;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

public class UsuarioVerificarActivity extends AppCompatActivity {

    PreferenciasManager mPreferenciasManager;
    AppCompatImageView imgIcon;
    Button btnAceptar;
    TextView txtEstado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPreferenciasManager = new PreferenciasManager(getApplicationContext());

        if (mPreferenciasManager.getValue(Utils.VERIFICADO)) {
            setContentView(R.layout.activity_usuario_verificar);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

            loadView();

            loadData();

            loadListener();

            getData();

        } else {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

    }

    private void loadListener() {
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

    }

    private void loadView() {
        btnAceptar = findViewById(R.id.btnAceptar);
        imgIcon = findViewById(R.id.imgIcon);
        txtEstado = findViewById(R.id.txtEstado);
    }

    private void loadData() {
        btnAceptar.setVisibility(View.GONE);
        txtEstado.setText("ESTADO: EN PROCESO DE VERIFICACIÓN");
        Glide.with(imgIcon.getContext()).load(R.drawable.ic_espera).into(imgIcon);
    }

    private void getData() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final Integer dni = mPreferenciasManager.getValueInt(Utils.DNI);
        db.collection("usuarios").document(String.valueOf(dni)).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Usuario usuario = Usuario.toMap(documentSnapshot.getData());
                        if (usuario != null) {
                            if (usuario.getEstado() == 1) {
                                btnAceptar.setVisibility(View.VISIBLE);
                                txtEstado.setText("ESTADO: VERIFICADO");
                                Glide.with(imgIcon.getContext()).load(R.drawable.ic_verificado).into(imgIcon);
                                mPreferenciasManager.setValue(String.format(Utils.VERIFICADO, dni), false);
                            }
                        } else {
                            Utils.showToast(getApplicationContext(), getString(R.string.noData));
                        }
                    }
                });
    }
}