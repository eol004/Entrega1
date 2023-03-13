package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

                /** Basado en el código extraido de "El informatico ibero"
                 * link: https://mpersonales.blogspot.com/2016/05/login-con-base-de-datos-sqlite-en.html
                 * Autor: Raul Tamani
                 * Modificado por Estefania Oñate para comprobar el inicio de sesión
                 */

                //Comprobar que en la base de datos coinciden los valores de usuario y contraseña
                Cursor c = miBaseDeDatos.getReadableDatabase().rawQuery("SELECT Usuario, Contrasena FROM Usuarios WHERE Usuario ='"+us+"'AND Contrasena ='"+con+"'", null); //https://www.develou.com/android-sqlite-bases-de-datos/#Cursores_en_SQLite
                try {
                    /*Condicional if preguntamos si cursor tiene algun dato*/
                    if(c.moveToFirst()){ //capturamos los valores del cursos y lo almacenamos en variable
                        String usua=c.getString(0);
                        String pass=c.getString(1);
                        //preguntamos si los datos ingresados son iguales
                        if (us.equals(usua)&&con.equals(pass)){
                            //si son iguales entonces va a Invitados.class
                            Intent intent=new Intent(InicioSesion.this, Invitados.class);
                            //Mandar a Invitados el usuario
                            intent.putExtra("usuario", us);
                            //lanzamos la actividad
                            startActivity(intent);
                            finish();
                        }
                    }//si la primera condicion no cumple entonces que envie un mensaje toast
                    else {
                        Toast toast=Toast.makeText(InicioSesion.this,"Datos incorrectos",Toast.LENGTH_LONG);
                        //mostramos el toast
                        toast.show();
                    }
                } catch (Exception e) {//capturamos los errores que hubieran
                    Toast toast=Toast.makeText(InicioSesion.this,"Error" + e.getMessage(),Toast.LENGTH_LONG);
                    //mostramos el mensaje
                    toast.show();
                }
                c.close();
            }
        });
    }
}