package com.example.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class Invitados extends AppCompatActivity {
    private TextView nombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invitados);
        //Recoger la variable usuario de InicioSesion para sacar el nombre
        String usuario = getIntent().getStringExtra("usuario");

        //Base de datos
        miBD miBaseDeDatos = new miBD(this, "miBaseDeDatos", null, 1);
        SQLiteDatabase db = miBaseDeDatos.getReadableDatabase();

        //Editar TextView con el nombre del usuario que ha iniciado sesión
        nombre = findViewById(R.id.nomusuarios);
        nombre.setText(miBaseDeDatos.obtenerNombre(usuario));

    }
}
