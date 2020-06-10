package com.example.gimnasio.Activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.gimnasio.Modelo.Usuario;
import com.example.gimnasio.R;
import com.example.gimnasio.Utils.PreferenciasManager;
import com.example.gimnasio.Utils.Utils;
import com.example.gimnasio.Utils.Validador;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtNombre, edtApellido, edtDni, edtCorreo, edtTelefono, edtContrasenia, edtContraseniaRepetir;
    Button btnRegister;
    ImageView btnBack;
    PreferenciasManager mPreferenciasManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        loadViews();

        loadData();

        loadListener();
    }

    private void loadListener() {
        btnRegister.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }

    private void loadData() {
        mPreferenciasManager = new PreferenciasManager(getApplicationContext());
    }

    private void loadViews() {
        edtNombre = findViewById(R.id.edtName);
        edtApellido = findViewById(R.id.edtApe);
        edtCorreo = findViewById(R.id.edtEmail);
        edtDni = findViewById(R.id.edtDni);
        edtTelefono = findViewById(R.id.edtTel);
        edtContrasenia = findViewById(R.id.edtPass);
        edtContraseniaRepetir = findViewById(R.id.edtRepass);
        btnBack = findViewById(R.id.btnBack);
        btnRegister = findViewById(R.id.btnRegister);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                onBackPressed();
                break;
            case R.id.btnRegister:
                register();
                break;
        }

    }

    private void register() {
        Validador validador = new Validador(getApplicationContext());

        String dni = edtDni.getText().toString().trim();
        String nombre = edtNombre.getText().toString().trim();
        String apellido = edtApellido.getText().toString().trim();
        String pass = edtContrasenia.getText().toString().trim();
        String rePass = edtContraseniaRepetir.getText().toString().trim();
        String correo = edtCorreo.getText().toString().trim();
        String telefono = edtTelefono.getText().toString().trim();
        String fecha = Utils.getFecha();
        int estado = 0;
        int tipoUsuario = 2;

        if (validador.validarDNI(edtDni) && validador.validarNombres(edtNombre) &&
                validador.validarNombres(edtApellido) && validador.validarContraseña(edtContrasenia, edtContraseniaRepetir)
                && validador.validarMail(edtCorreo) && validador.validarTelefono(edtTelefono)) {

            Usuario usuario = new Usuario(Integer.parseInt(dni), nombre, apellido, fecha, pass, rePass, telefono, correo,
                    estado, tipoUsuario);

            sendData(usuario);

        } else {
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
                Utils.showToast(getApplicationContext(), "¡Inicia sesión para verificar!");
                finish();
            }
        });
    }
}
