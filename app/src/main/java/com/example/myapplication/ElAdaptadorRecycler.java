package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ElAdaptadorRecycler extends RecyclerView.Adapter<ElViewHolder> {

    private String[] losnombres;
    private int[] lasimagenes;

    public ElAdaptadorRecycler (String[] nombres, int[] imagenes){
        this.losnombres=nombres;
        this.lasimagenes=imagenes;
    }

    @NonNull
    @Override
    public ElViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View elLayoutDeCadaItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.trago_layout, parent, false);
        return new ElViewHolder(elLayoutDeCadaItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ElViewHolder holder, int position) {
        holder.texto.setText(losnombres[position]);
        holder.trago.setImageResource(lasimagenes[position]);
    }

    @Override
    public int getItemCount() {
        return lasimagenes.length;
        //return 0;
    }
}
