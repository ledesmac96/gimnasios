package com.example.gimnasio.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gimnasio.Modelo.Turno;
import com.example.gimnasio.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TurnosAdapter extends RecyclerView.Adapter<TurnosAdapter.EventosViewHolder> {
    private ArrayList<Turno> mTurnos;
    private Context context;

    public TurnosAdapter(ArrayList<Turno> list, Context ctx) {
        this.mTurnos = list;
        this.context = ctx;
    }

    @NonNull
    @Override
    public EventosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_turno, parent, false);

        return new EventosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventosViewHolder holder, int position) {
        Turno torneo = mTurnos.get(position);

        holder.id.setText(torneo.getIdTurno());

    }


    @Override
    public long getItemId(int position) {
        return mTurnos.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return mTurnos.size();
    }

    static class EventosViewHolder extends RecyclerView.ViewHolder {

        TextView id, fecha, hIni, hFin, cantPer;

        EventosViewHolder(View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.txtIdTurno);
            fecha = itemView.findViewById(R.id.txtFecha);
            hIni = itemView.findViewById(R.id.txtHoraI);
            hFin = itemView.findViewById(R.id.txtHoraF);
            cantPer = itemView.findViewById(R.id.txtCant);
        }
    }

    public void change(ArrayList<Turno> list) {
        mTurnos = list;
        notifyDataSetChanged();
    }
}
