package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Llama al método que cerrará la pantalla de bienvenida después de 5 segundos
                cerrarPantallaBienvenida();
            }
        }, 5000); // 5000 milisegundos = 5 segundos
    }
    private void cerrarPantallaBienvenida(){
        Intent intent = new Intent(this, InicioSesion.class);
        startActivity(intent);
        finish();
    }
}
