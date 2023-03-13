package com.example.myapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class Registro extends AppCompatActivity implements BlankFragment.Listener {
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        //Base de datos
        miBD miBaseDeDatos = new miBD(this, "miBaseDeDatos", null, 1);
        SQLiteDatabase bd = miBaseDeDatos.getWritableDatabase();
        //Datos a añadir
        EditText usuario = findViewById(R.id.usuario_reg);
        EditText contr = findViewById(R.id.contra_reg);
        EditText nombre = findViewById(R.id.nom_reg);
        //Añadir a la BD al pulsar el boton de Registro
        Button btn_registrarse = findViewById(R.id.btn_registrarse);
        btn_registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                miBaseDeDatos.anadirUsuario(usuario.getText().toString(), nombre.getText().toString(), contr.getText().toString(), 0);
            }
        });
        //Cuando se de al boton de iniciar sesion lleva a la pantalla de inicio sesión
        Button btn_inicioses = findViewById(R.id.btn_inicioses_reg);
        btn_inicioses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), InicioSesion.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
