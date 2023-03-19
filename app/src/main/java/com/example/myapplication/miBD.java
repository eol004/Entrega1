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
        sqLiteDatabase.execSQL("CREATE TABLE Invitados(id INTEGER PRIMARY KEY AUTOINCREMENT, Jugador1 VARCHAR(255), Tragos INT)");
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
        db.insert("Invitados", null, nuevo);
        db.close();
    }

    public void anadirTragosPerdedorInv(String perdedorInv, int tragos){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Tragos", tragos);

        String whereClause = "Jugador1 = ?";
        String[] whereArgs = new String[] { perdedorInv };

        // Actualizar los registros que cumplan la cláusula WHERE
        int rowsAffected = db.update("Invitados", values, whereClause, whereArgs);
        System.out.println(rowsAffected);
        // Cerrar la conexión a la base de datos
        db.close();
    }
    public void anadirTragosPerdedorUsu(String perdedorUsu, int tragos){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Tragos", tragos);

        String whereClause = "Usuario = ?";
        String[] whereArgs = new String[] { perdedorUsu };

        // Actualizar los registros que cumplan la cláusula WHERE
        int rowsAffected = db.update("Usuarios", values, whereClause, whereArgs);
        // Cerrar la conexión a la base de datos
        db.close();
    }


}
