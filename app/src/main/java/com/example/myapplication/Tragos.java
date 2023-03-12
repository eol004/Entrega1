package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


public class Tragos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int[] tragos= {R.drawable.bo1, R.drawable.bo2, R.drawable.bo3, R.drawable.bo4};
        String[] nombres={"1 TRAGO","2 TRAGOS", "3 TRAGOS", "4 TRAGOS"};

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tragos);

        RecyclerView lalista=findViewById(R.id.elreciclerview);
        ElAdaptadorRecycler eladaptador = new ElAdaptadorRecycler(nombres,tragos);
        lalista.setAdapter(eladaptador);

        GridLayoutManager elLayoutFinal= new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        lalista.setLayoutManager(elLayoutFinal);
    }
}