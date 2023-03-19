package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000); // 5000 milisegundos = 5 segundos
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(Inicio.this, Registro.class);
                startActivity(intent);
                finish();
            }
        });

    }

}
