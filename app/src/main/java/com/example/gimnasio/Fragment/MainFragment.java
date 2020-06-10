package com.example.gimnasio.Fragment;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gimnasio.Modelo.Noticia;
import com.example.gimnasio.R;

import java.util.ArrayList;

public class MainFragment extends Fragment {

    View view;

    public MainFragment() {
        // Metodo necesario
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Crea la vista de Inicio
        view = inflater.inflate(R.layout.main_fragment, container, false);

        loadViews();

        loadDataRecycler();

        return view;
    }

    private void loadViews() {

    }

    private void loadDataRecycler() {


    }


}
