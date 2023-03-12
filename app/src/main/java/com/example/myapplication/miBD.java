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
        sqLiteDatabase.execSQL("CREATE TABLE Usuarios(Usuario INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Nombre VARCHAR(255), Contrasena VARCHAR(255), Tragos INT)");
        //Tabla Jugadores
        sqLiteDatabase.execSQL("CREATE TABLE Invitado(Jugador1 VARCHAR(255), Tragos INT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void anadirUsuario(String nombre, String usuario, String contrasena, Integer trago) {
        SQLiteDatabase db = this.getWritableDatabase();
        //Contenido nuevo
        ContentValues nuevo = new ContentValues();
        nuevo.put("Nombre", nombre);
        nuevo.put("Usuario", usuario);
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

    @SuppressLint("Range")
    public String obtenerNombre(String usuario){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT Nombre FROM Usuarios WHERE Usuario= ?", new String[]{usuario});
        String nom;
        if (c.moveToFirst()){ //Si existe primer registro
            return nom = c.getString(c.getColumnIndex("Nombre"));
        }
        return null;
    }

}
