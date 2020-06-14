package com.example.gimnasio.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gimnasio.Modelo.Noticia;
import com.example.gimnasio.Modelo.Turno;
import com.example.gimnasio.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NoticiasAdapter extends RecyclerView.Adapter<NoticiasAdapter.EventosViewHolder> {
    private ArrayList<Noticia> mNoticias;
    private Context context;

    public NoticiasAdapter(ArrayList<Noticia> list, Context ctx) {
        this.mNoticias = list;
        this.context = ctx;
    }

    @NonNull
    @Override
    public NoticiasAdapter.EventosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_noticia, parent, false);

        return new NoticiasAdapter.EventosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticiasAdapter.EventosViewHolder holder, int position) {
        Noticia noticia = mNoticias.get(position);

        holder.txtTitulo.setText(noticia.getTitulo());
        if (noticia.getCuerpo().length() > 100) {
            holder.txtDescripcion.setText(noticia.getCuerpo().substring(0, 100));
        } else
            holder.txtDescripcion.setText(noticia.getCuerpo());
        holder.txtFecha.setText(noticia.getFechahora());
        //holder.etiqueta.setText(noticia.getEtiqueta());
        Glide.with(holder.imgFoto.getContext()).load(noticia.getUrlImagen()).into(holder.imgFoto);

    }


    @Override
    public long getItemId(int position) {
        return Integer.getInteger(mNoticias.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return mNoticias.size();
    }

    static class EventosViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitulo, txtDescripcion, txtFecha, etiqueta;
        ImageView imgFoto;

        EventosViewHolder(View itemView) {
            super(itemView);

            txtDescripcion = itemView.findViewById(R.id.txtCuerpo);
            txtTitulo = itemView.findViewById(R.id.txtTitulo);
            txtFecha = itemView.findViewById(R.id.txtFecha);
            imgFoto = itemView.findViewById(R.id.imgAlerta);
            etiqueta = itemView.findViewById(R.id.txtEtiqueta);
        }
    }

}
