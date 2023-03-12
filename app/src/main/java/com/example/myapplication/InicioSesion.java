package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class InicioSesion extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_sesion);
        //Base de datos
        miBD miBaseDeDatos = new miBD(this, "miBaseDeDatos", null, 1);
        //Credenciales para comprobar en la base de datos
        EditText usuario = findViewById(R.id.nomusuario_is);
        EditText contr = findViewById(R.id.contra_is);

        Button btn_inicses = findViewById(R.id.btn_incioses);
        btn_inicses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String us = usuario.getText().toString();
                String con = contr.getText().toString();

                //Comprobar que en la base de datos coinciden los valores de usuario y contraseña
                Cursor ca = miBaseDeDatos.getReadableDatabase().rawQuery("SELECT COUNT(*) FROM Usuarios", null);
                if (ca != null && ca.moveToFirst()) {
                    int count = ca.getInt(0);
                    Log.d("TAG", "Número de registros en la tabla: " + count);
                }

                Cursor c = miBaseDeDatos.getReadableDatabase().rawQuery("SELECT COUNT(*) FROM Usuarios WHERE Nombre = ? AND Contrasena = ?", new String[]{us, con}); //https://www.develou.com/android-sqlite-bases-de-datos/#Cursores_en_SQLite
                if (c.getCount() > 0) { //Si hay registro pasar a Invitados
                    Intent intent = new Intent(InicioSesion.this, Invitados.class);
                    //Mandar a Invitados el usuario
                    intent.putExtra("usuario", us);
                    startActivity(intent);
                    finish();
                } else { //Mensaje de error si el usuario y la contraseña no coincide
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle("Inicio de sesión fallido");
                    builder.setMessage("Usuario o contraseña incorrectos. ¡Vuelve a intentarlo! :) ");
                    builder.setPositiveButton("Nuevo intento", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss(); //https://es.stackoverflow.com/questions/130576/cerrar-alertdialog
                        }

                    });
                }
            }
        });
    }
}