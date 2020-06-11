package com.example.gimnasio.Fragment;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gimnasio.R;

public class PerfilAdminFragment extends Fragment {

    private View view;
    private TextView txtPrecio;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_perfil_admin, container, false);

        loadViews();
        loadData();
        return view;
    }

    private void loadData() {


    }


    private void loadViews() {
        //txtPrecio = view.findViewById(R.id.scrollingtext);

    }


}
