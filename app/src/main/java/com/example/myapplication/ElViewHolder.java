package com.example.myapplication;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ElViewHolder extends RecyclerView.ViewHolder {
    public TextView texto;
    public ImageView trago;

    public ElViewHolder(View itemView) {
        super(itemView);
        texto = itemView.findViewById(R.id.texto);
        trago = itemView.findViewById(R.id.trago);
    }
}
