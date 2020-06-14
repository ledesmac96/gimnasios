package com.example.gimnasio.Fragment;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.gimnasio.Adapters.NoticiasAdapter;
import com.example.gimnasio.Adapters.OpcionesAdapter;
import com.example.gimnasio.Modelo.Noticia;
import com.example.gimnasio.Modelo.Opciones;
import com.example.gimnasio.R;

import java.util.ArrayList;

public class MainFragment extends Fragment {

    View view;
    RecyclerView.LayoutManager mLayoutManager, mLayoutManagerOp;
    RecyclerView recyclerNoticias, mRecyclerViewFunciones;
    ArrayList<Noticia> mNoticias;
    NoticiasAdapter mNoticiasAdapter;
    OpcionesAdapter mAdapter;
    ArrayList<Opciones> mList;

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
        recyclerNoticias = view.findViewById(R.id.recyclerNoticias);
        mRecyclerViewFunciones = view.findViewById(R.id.recyclerCards);
    }

    private void loadDataRecycler() {

        loadNoticias();

        mNoticiasAdapter = new NoticiasAdapter(mNoticias, getContext());
        mLayoutManager = new LinearLayoutManager(getContext());
        recyclerNoticias.setLayoutManager(mLayoutManager);

        recyclerNoticias.setAdapter(mNoticiasAdapter);
        recyclerNoticias.setNestedScrollingEnabled(false);

//        ItemClickSupport itemClickSupport2 = ItemClickSupport.addTo(recyclerNoticias);
//        itemClickSupport2.setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
//            @Override
//            public void onItemClick(RecyclerView parent, View view, int position, long id) {
//                Intent i = new Intent(getContext(), NoticiaLectorActivity.class);
//                i.putExtra(Utils.NOTICIA, mNoticias.get(position));
//                startActivity(i);
//            }
//        });

        mList = new ArrayList<>();
        mList.add(new Opciones(1, "Miembros", R.drawable.ic_miembros, R.color.white, 66));
        mList.add(new Opciones(1, "Vencen en 7 días", R.drawable.ic_fecha_limite, R.color.white, 14));
        mList.add(new Opciones(1, "Expirados", R.drawable.ic_vencido, R.color.white, 5));
        mList.add(new Opciones(1, "Miembros nuevos", R.drawable.ic_new_user, R.color.white, 9));
        mList.add(new Opciones(1, "OP 5", R.drawable.ic_menu, R.color.white, 0));
        mList.add(new Opciones(1, "OP 6", R.drawable.ic_menu, R.color.white, 0));

        mAdapter = new OpcionesAdapter(mList, getContext());
        mLayoutManagerOp = new GridLayoutManager(getContext(), 3);
        mRecyclerViewFunciones.setLayoutManager(mLayoutManagerOp);
        mRecyclerViewFunciones.setHasFixedSize(true);
        mRecyclerViewFunciones.setAdapter(mAdapter);

    }

    private void loadNoticias() {
        mNoticias = new ArrayList<>();

        Noticia noticia = new Noticia("El 24/06 el gimnasio permanecerá cerrado",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor\n incididunt ut labore et dolore magna aliqua.",
                "Publicado el: 15/06/20 - 13:45","https://www.alwaysgym.com.ar/wp-content/uploads/2014/04/zz_crossfit1.jpg",
                "1");
        mNoticias.add(noticia);

        noticia = new Noticia("Vuelven las actividades tras la cuarentena",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor\n incididunt ut labore et dolore magna aliqua.",
                "Publicado el: 15/06/20 - 13:45","https://www.alwaysgym.com.ar/wp-content/uploads/2016/04/entrenamiento_personalizado1.jpg",
                "2");
        mNoticias.add(noticia);

        noticia = new Noticia("Desafío de abdominales de 30 minutos",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor\n incididunt ut labore et dolore magna aliqua.",
                "Publicado el: 15/06/20 - 13:45","https://www.alwaysgym.com.ar/wp-content/uploads/2014/04/spinning1.jpg",
                "3");
        mNoticias.add(noticia);

    }

}
