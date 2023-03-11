package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


public class Tragos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tragos);

        RecyclerView lalista=findViewById(R.id.elreciclerview);
        int[] tragos= {R.drawable.borracha1, R.drawable.borracha2, R.drawable.borracho3, R.drawable.borracho4};
        String[] nombres={"1 TRAGO","2 TRAGOS", "3 TRAGOS", "4 TRAGOS"};
        ElAdaptadorRecycler eladaptador = new ElAdaptadorRecycler(nombres,tragos);
        lalista.setAdapter(eladaptador);
        GridLayoutManager elLayoutFinal= new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        lalista.setLayoutManager(elLayoutFinal);
    }
}