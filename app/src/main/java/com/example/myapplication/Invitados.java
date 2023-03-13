package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Invitados extends AppCompatActivity {
    private TextView nombre;
    private EditText nomInv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invitados);
        nombre = findViewById(R.id.nomusuarios);
        String usuario;

        //Recoger la variable usuario de InicioSesion para sacar el nombre de ese usuario
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            nombre.setText(extras.getString("usuario"));
        }
        //Base de datos
        miBD miBaseDeDatos = new miBD(this, "miBaseDeDatos", null, 1);
        SQLiteDatabase db = miBaseDeDatos.getReadableDatabase();

        //Editar TextView con el nombre del usuario que ha iniciado sesión
        nomInv = findViewById(R.id.invitado);

        Button btn_jugar = findViewById(R.id.btn_jugar);
        btn_jugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(nomInv.getText().toString())) {
                    miBaseDeDatos.anadirJugadores(nomInv.getText().toString(), 0);
                    Intent intent=new Intent(Invitados.this, MainActivity.class);
                    //Mandar al juego el usuario
                    intent.putExtra("usuario", extras.getString("usuario"));
                    //Mandar al juego el invitado
                    intent.putExtra("invitado", nomInv.getText().toString());
                    //lanzamos la actividad
                    startActivity(intent);
                    finish();
                } else {
                    Toast toast = Toast.makeText(Invitados.this, "Introduce un jugador", Toast.LENGTH_LONG);
                    //mostramos el mensaje
                    toast.show();
                }
            }
        });
    }
}