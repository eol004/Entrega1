package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Tragos extends AppCompatActivity {
    int numSeleccionTragos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //RECYCLERVIEW + CARDVIEW
        //
        int[] tragos = {R.drawable.bo1, R.drawable.bo2, R.drawable.bo3, R.drawable.bo4};
        String[] nombres = {"1 TRAGO", "2 TRAGOS", "3 TRAGOS", "4 TRAGOS"};

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tragos);

        RecyclerView lalista = findViewById(R.id.elreciclerview);
        ElAdaptadorRecycler eladaptador = new ElAdaptadorRecycler(nombres, tragos);
        //Mostrar en textView
        TextView texto = findViewById(R.id.texto);
        texto.setText("");
        Bundle extras = getIntent().getExtras();
        String ganadorUsu = extras.getString("ganadorUsu");
        String perdedorUsu = extras.getString("perdedorUsu");
        String ganadorInv = extras.getString("ganadorInv");
        String perdedorInv = extras.getString("perdedorInv");
        eladaptador.setOnItemClickListener(new ElAdaptadorRecycler.OnItemClickListener() {
            @Override
            public void onItemClick(int position, int numTragos) {
                if (position == 0) {
                    numSeleccionTragos = 1;
                    if (ganadorUsu != null && perdedorInv != null) { //Si ha ganado el usuario, sumar tragos al invitado que ha perdido
                        String editado = perdedorInv + ", " + ganadorUsu + " ha ganado, bebes " + numSeleccionTragos + " trago!!!!";
                        texto.setText(editado); //En la etiqueta poner quien bebe y cuantos tragos
                    } else if (ganadorInv != null && perdedorUsu != null) { //Si ha ganado el invitado, sumar tragos al usuario que ha perdido
                        String editado = perdedorUsu + ", " + ganadorInv + " ha ganado, bebes " + numSeleccionTragos + " trago!!!!";
                        texto.setText(editado); //En la etiqueta poner quien bebe y cuantos tragos
                    }
                } else if (position == 1) {
                    numSeleccionTragos = 2;
                    if (ganadorUsu != null && perdedorInv != null) { //Si ha ganado el usuario, sumar tragos al invitado que ha perdido
                        String editado = perdedorInv + ", " + ganadorUsu + " ha ganado, bebes " + numSeleccionTragos + " tragos!!!!";
                        texto.setText(editado); //En la etiqueta poner quien bebe y cuantos tragos
                    } else if (ganadorInv != null && perdedorUsu != null) { //Si ha ganado el invitado, sumar tragos al usuario que ha perdido
                        String editado = perdedorUsu + ", " + ganadorInv + " ha ganado, bebes " + numSeleccionTragos + " tragos!!!!";
                        texto.setText(editado); //En la etiqueta poner quien bebe y cuantos tragos
                    }
                } else if (position == 2) {
                    numSeleccionTragos = 3;
                    if (ganadorUsu != null && perdedorInv != null) { //Si ha ganado el usuario, sumar tragos al invitado que ha perdido
                        String editado = perdedorInv + ", " + ganadorUsu + " ha ganado, bebes " + numSeleccionTragos + " tragos!!!!";
                        texto.setText(editado); //En la etiqueta poner quien bebe y cuantos tragos
                    } else if (ganadorInv != null && perdedorUsu != null) { //Si ha ganado el invitado, sumar tragos al usuario que ha perdido
                        String editado = perdedorUsu + ", " + ganadorInv + " ha ganado, bebes " + numSeleccionTragos + " tragos!!!!";
                        texto.setText(editado); //En la etiqueta poner quien bebe y cuantos tragos
                    }
                } else if (position == 3) {
                    numSeleccionTragos = 4;
                    if (ganadorUsu != null && perdedorInv != null) { //Si ha ganado el usuario, sumar tragos al invitado que ha perdido
                        String editado = perdedorInv + ", " + ganadorUsu + " ha ganado, bebes " + numSeleccionTragos + " tragos!!!!";
                        texto.setText(editado); //En la etiqueta poner quien bebe y cuantos tragos
                    } else if (ganadorInv != null && perdedorUsu != null) { //Si ha ganado el invitado, sumar tragos al usuario que ha perdido
                        String editado = perdedorUsu + ", " + ganadorInv + " ha ganado, bebes " + numSeleccionTragos + " tragos!!!!";
                        texto.setText(editado); //En la etiqueta poner quien bebe y cuantos tragos
                    }
                }
            }
        });
        lalista.setAdapter(eladaptador);
        GridLayoutManager elLayoutFinal = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        lalista.setLayoutManager(elLayoutFinal);

        //Iniciar otra partida
        Button btn_jugarDeNuevo = findViewById(R.id.jugarDeNuevo);
        btn_jugarDeNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Tragos.this, InicioSesion.class);
                startActivity(intent);
            }
        });
    }
}