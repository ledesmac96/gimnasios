package com.example.gimnasio.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.example.gimnasio.Modelo.Usuario;
import com.example.gimnasio.R;
import com.example.gimnasio.Utils.PreferenciasManager;
import com.example.gimnasio.Utils.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class UsuarioVerificarActivity extends AppCompatActivity {

    PreferenciasManager mPreferenciasManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPreferenciasManager = new PreferenciasManager(getApplicationContext());

        if (mPreferenciasManager.getValue(Utils.VERIFICADO)){
            setContentView(R.layout.activity_main);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

            getData();

        }else {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

    }

    private void getData() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final Integer dni = mPreferenciasManager.getValueInt(Utils.DNI);
        db.collection("usuarios").document(String.valueOf(dni)).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Usuario usuario = documentSnapshot.toObject(Usuario.class);
                if (usuario != null){
                    if (usuario.getEstado() == 1){
                        mPreferenciasManager.setValue(String.format(Utils.VERIFICADO, dni), false);
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
                }else{
                    Utils.showToast(getApplicationContext(), getString(R.string.noData));
                }
            }
        });
    }
}