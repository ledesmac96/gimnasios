package com.example.gimnasio.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gimnasio.Modelo.Opciones;
import com.example.gimnasio.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.view.View.GONE;

public class OpcionesAdapter extends RecyclerView.Adapter<OpcionesAdapter.OpcionesViewHolder> {

    private ArrayList<Opciones> arrayList;
    private Context context;

    public OpcionesAdapter(ArrayList<Opciones> list, Context ctx) {
        context = ctx;
        arrayList = list;
    }


    @Override
    public long getItemId(int position) {
        return arrayList.get(position).getId();
    }

    @NonNull
    @Override
    public OpcionesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_opciones, parent, false);


        return new OpcionesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OpcionesViewHolder holder, int position) {

        Opciones s = arrayList.get(position);

        holder.txtTitulo.setText(s.getTitulo());
        if (s.getColorText() != 0)
            holder.txtTitulo.setTextColor(context.getResources().getColor(s.getColorText()));
        if (s.getSizeText() != 0)
            holder.txtTitulo.setTextSize(Float.parseFloat(String.valueOf(s.getSizeText())));
        if (s.getIcon() != 0)
            Glide.with(holder.imgIcono.getContext())
                    .load(s.getIcon())
                    .into(holder.imgIcono);
        else
            holder.imgIcono.setVisibility(GONE);
        holder.mCardView.setCardBackgroundColor(context.getResources().getColor(s.getColor()));
        holder.txtCant.setText(String.valueOf(s.getCantidad()));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    static class OpcionesViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitulo, txtCant;
        ImageView imgIcono;
        CardView mCardView;
        LinearLayout mLinearLayout;
        View mView;

        OpcionesViewHolder(View itemView) {
            super(itemView);

            imgIcono = itemView.findViewById(R.id.imgIcono);
            txtTitulo = itemView.findViewById(R.id.txtTitulo);
            txtCant = itemView.findViewById(R.id.txtCant);
            mCardView = itemView.findViewById(R.id.card);
            mLinearLayout = itemView.findViewById(R.id.layout);

        }
    }

}
