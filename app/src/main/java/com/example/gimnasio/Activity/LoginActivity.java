package com.example.gimnasio.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.gimnasio.Modelo.Usuario;
import com.example.gimnasio.R;
import com.example.gimnasio.Utils.PreferenciasManager;
import com.example.gimnasio.Utils.Utils;
import com.example.gimnasio.Utils.Validador;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.okhttp.internal.Internal;

import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtUser, edtContrasenia;
    Button btnLogin;
    TextView btnRegister;
    ImageView imgBack;
    ProgressBar progresarLogin;
    PreferenciasManager mPreferenciasManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPreferenciasManager = new PreferenciasManager(getApplicationContext());

        if (mPreferenciasManager.isFirstTimeLaunch()) {
            setContentView(R.layout.activity_login);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

            loadViews();

            loadListener();

            loadData();

        } else if (mPreferenciasManager.getValue(
                String.format(Utils.VERIFICADO,
                        mPreferenciasManager.getValueInt(Utils.DNI)))) {
            startActivity(new Intent(getApplicationContext(), UsuarioVerificarActivity.class));
            finish();
        } else {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }


    }

    private void loadData() {
        mPreferenciasManager = new PreferenciasManager(getApplicationContext());
        progresarLogin.setVisibility(View.GONE);
    }


    private void loadListener() {
        btnRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        imgBack.setOnClickListener(this);

    }

    private void loadViews() {
        edtUser = findViewById(R.id.edtUser);
        edtContrasenia = findViewById(R.id.edtPass);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        imgBack = findViewById(R.id.btnBack);
        progresarLogin = findViewById(R.id.progress_bar);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                onBackPressed();
                break;
            case R.id.btnRegister:
                startActivity(new Intent(getApplicationContext(), RegistroActivity.class));
                break;
            case R.id.btnLogin:
                login();
                break;
        }
    }

    private void login() {

        Validador validador = new Validador(getApplicationContext());

        String user = edtUser.getText().toString().trim();
        String pass = edtContrasenia.getText().toString().trim();

        if (validador.validarDNI(edtUser) && validador.validarContrasenia(edtContrasenia)) {
            login(user, pass);
            btnRegister.setEnabled(false);
            progresarLogin.setVisibility(View.VISIBLE);
            btnLogin.setVisibility(View.GONE);
        } else {
            Utils.showToast(getApplicationContext(), getString(R.string.camposIncompletos));
        }

    }

    private void login(String user, final String pass) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("usuarios").document(user).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Map<String, Object> mapa = documentSnapshot.getData();
                        if (mapa != null) {
                            Usuario usuario = Usuario.toMap(mapa);
                            if (usuario.getPassword().equals(pass)) {
                                mPreferenciasManager.setValue(Utils.DNI, usuario.getDni());
                                mPreferenciasManager.setFirstTimeLaunch(false);
                                startActivity(new Intent(getApplicationContext(), UsuarioVerificarActivity.class));
                                finish();

                            } else {
                                Utils.showToast(getApplicationContext(), getString(R.string.contraseniaIncorrecta));
                                btnRegister.setEnabled(true);
                                progresarLogin.setVisibility(View.GONE);
                                btnLogin.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Utils.showToast(getApplicationContext(), getString(R.string.usuarioNoExiste));
                            btnRegister.setEnabled(true);
                            progresarLogin.setVisibility(View.GONE);
                            btnLogin.setVisibility(View.VISIBLE);
                        }

                    }
                });
    }
}
