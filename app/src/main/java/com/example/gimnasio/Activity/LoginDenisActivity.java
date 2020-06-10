package com.example.gimnasio.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.example.gimnasio.Modelo.Usuario;
import com.example.gimnasio.Utils.PreferenciasManager;
import com.example.gimnasio.Utils.Utils;
import com.example.gimnasio.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class LoginDenisActivity extends AppCompatActivity {

    PreferenciasManager mPreferenciasManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPreferenciasManager = new PreferenciasManager(getApplicationContext());

        if (mPreferenciasManager.isFirstTimeLaunch()){
            setContentView(R.layout.activity_main);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        }else if (mPreferenciasManager.getValue(
                String.format(Utils.VERIFICADO,
                        mPreferenciasManager.getValueInt(Utils.DNI)))){
            startActivity(new Intent(getApplicationContext(), UsuarioVerificarActivity.class));
            finish();
        }else {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }



    }

    private void login(String user, final String pass) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("usuarios").document(user).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Usuario usuario = documentSnapshot.toObject(Usuario.class);
                        if (usuario != null){
                            if (usuario.getPassword().equals(pass)){
                                mPreferenciasManager.setValue(Utils.DNI, usuario.getDni());
                                startActivity(new Intent(getApplicationContext(), UsuarioVerificarActivity.class));
                                finish();
                            }
                        }else{
                            Utils.showToast(getApplicationContext(), getString(R.string.noData));
                        }
                    }
                });
    }

    public void createUser(){
        String dni = "40657677";
        String nombre = "Denis";
        String apellido = "Acosta";
        String pass = "123445";
        String fecha = Utils.getFecha();
        Integer estado = 0;
        Integer tipoUsuario = 2;

        if(!nombre.equals("") && !apellido.equals("") && !dni.equals("") && !pass.equals("")){

            Usuario usuario = new Usuario(Integer.parseInt(dni), nombre, apellido, fecha, pass, estado, tipoUsuario);

            sendData(usuario);

        }else{
            Utils.showToast(getApplicationContext(), getString(R.string.camposIncompletos));
        }
    }
    private void sendData(final Usuario usuario) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("usuarios").document(String.valueOf(usuario.getDni())).set(usuario)
                .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Utils.showToast(getApplicationContext(), "¡Error al registrarse!");
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Utils.showToast(getApplicationContext(), "¡Registrado!");
                mPreferenciasManager.setValue(Utils.DNI, usuario.getDni());
                mPreferenciasManager.setFirstTimeLaunch(false);
                //startActivity(new Intent(getApplicationContext(), UsuarioVerificarActivity.class));
            }
        });
    }
}
