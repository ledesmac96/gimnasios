package com.example.gimnasio.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gimnasio.Modelo.Cuota;
import com.example.gimnasio.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CuotasAdapter extends RecyclerView.Adapter<CuotasAdapter.EventosViewHolder> {
    private ArrayList<Cuota> mCuotas;
    private Context context;

    public CuotasAdapter(ArrayList<Cuota> list, Context ctx) {
        this.mCuotas = list;
        this.context = ctx;
    }

    @NonNull
    @Override
    public CuotasAdapter.EventosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_turno, parent, false);

        return new CuotasAdapter.EventosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CuotasAdapter.EventosViewHolder holder, int position) {
        Cuota cuota = mCuotas.get(position);
        float monto = -1;
        float[] m = cuota.getMonto();

        holder.fecha.setText(cuota.getFechaPago());
        for (int i = 0; i < m.length; i++) {
            monto = monto + m[i];
        }
        holder.monto.setText(String.valueOf(monto));

    }


    @Override
    public long getItemId(int position) {
        return mCuotas.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return mCuotas.size();
    }

    static class EventosViewHolder extends RecyclerView.ViewHolder {

        TextView fecha, monto;

        EventosViewHolder(View itemView) {
            super(itemView);

            fecha = itemView.findViewById(R.id.txtFecha);
            monto = itemView.findViewById(R.id.txtMonto);

        }
    }

    public void change(ArrayList<Cuota> list) {
        mCuotas = list;
        notifyDataSetChanged();
    }
}
