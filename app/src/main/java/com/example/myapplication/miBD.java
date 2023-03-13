package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class miBD extends SQLiteOpenHelper {

    public miBD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Tabla Usuarios
        sqLiteDatabase.execSQL("CREATE TABLE Usuarios(Usuario VARCHAR(255) PRIMARY KEY, Nombre VARCHAR(255), Contrasena VARCHAR(255), Tragos INTEGER)");
        //Tabla Jugadores
        sqLiteDatabase.execSQL("CREATE TABLE Invitado(Jugador1 VARCHAR(255), Tragos INT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void anadirUsuario(String usuario, String nombre, String contrasena, Integer trago) {
        SQLiteDatabase db = this.getWritableDatabase();
        //Contenido nuevo
        ContentValues nuevo = new ContentValues();
        nuevo.put("Usuario", usuario);
        nuevo.put("Nombre", nombre);
        nuevo.put("Contrasena", contrasena);
        nuevo.put("Tragos", trago);
        //Insercion
        db.insert("Usuarios", null, nuevo);
        db.close();
    }
    public void anadirJugadores(String jugador1, Integer trago){
        SQLiteDatabase db = this.getWritableDatabase();
        //Contenido nuevo
        ContentValues nuevo = new ContentValues();
        nuevo.put("Jugador1", jugador1);
        nuevo.put("Tragos", trago);
        //Insercion
        db.insert("Invitado", null, nuevo);
        db.close();
    }


}
