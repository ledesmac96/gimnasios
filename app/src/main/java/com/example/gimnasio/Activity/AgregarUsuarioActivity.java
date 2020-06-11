package com.example.gimnasio.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gimnasio.Modelo.Usuario;
import com.example.gimnasio.R;
import com.example.gimnasio.Utils.PreferenciasManager;
import com.example.gimnasio.Utils.Utils;
import com.example.gimnasio.Utils.Validador;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class AgregarUsuarioActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView imgIcono;
    EditText edtNombre, edtApellido, edtDni, edtCorreo, edtTelefono, edtContrasenia, edtContraseniaRepetir;
    Button btnRegister;
    PreferenciasManager mPreferenciasManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_usuario);

        loadViews();

        loadListener();

        loadData();

        setToolbar();

    }

    private void loadData() {
        mPreferenciasManager = new PreferenciasManager(getApplicationContext());
    }

    private void loadListener() {
        btnRegister.setOnClickListener(this);
        imgIcono.setOnClickListener(this);
    }

    private void loadViews() {
        imgIcono = findViewById(R.id.imgFlecha);
        edtNombre = findViewById(R.id.edtName);
        edtApellido = findViewById(R.id.edtApe);
        edtCorreo = findViewById(R.id.edtEmail);
        edtDni = findViewById(R.id.edtDni);
        edtTelefono = findViewById(R.id.edtTel);
        edtContrasenia = findViewById(R.id.edtPass);
        edtContraseniaRepetir = findViewById(R.id.edtRepass);
        btnRegister = findViewById(R.id.btnRegister);
    }

    private void setToolbar() {
        ((TextView) findViewById(R.id.txtTitulo)).setText("Agregar cliente");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgFlecha:
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
