package com.example.gimnasio.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gimnasio.Modelo.Turno;
import com.example.gimnasio.Modelo.Usuario;
import com.example.gimnasio.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UsuariosAdapter extends RecyclerView.Adapter<UsuariosAdapter.EventosViewHolder>{
    private ArrayList<Usuario> mUsuarios;
    private Context context;

    public UsuariosAdapter(ArrayList<Usuario> list, Context ctx) {
        this.mUsuarios = list;
        this.context = ctx;
    }

    @NonNull
    @Override
    public UsuariosAdapter.EventosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_usuarios, parent, false);

        return new UsuariosAdapter.EventosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuariosAdapter.EventosViewHolder holder, int position) {
        Usuario usuario = mUsuarios.get(position);

        String name = usuario.getNombre() + " " + usuario.getApellido();
        holder.name.setText(name);
        holder.dni.setText(String.valueOf(usuario.getDni()));

    }


    @Override
    public long getItemId(int position) {
        return mUsuarios.get(position).getDni();
    }

    @Override
    public int getItemCount() {
        return mUsuarios.size();
    }

    static class EventosViewHolder extends RecyclerView.ViewHolder {

        TextView dni, name;

        EventosViewHolder(View itemView) {
            super(itemView);

            dni = itemView.findViewById(R.id.txtDni);
            name = itemView.findViewById(R.id.txtName);
        }
    }

    public void change(ArrayList<Usuario> list) {
        mUsuarios = list;
        notifyDataSetChanged();
    }
}
