package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.Manifest;


public class Tragos extends AppCompatActivity {
    int numSeleccionTragos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //RECYCLERVIEW + CARDVIEW
        //
        int[] tragos = {R.drawable.bo1, R.drawable.bo2, R.drawable.bo3, R.drawable.bo4};
        String[] nombres = {"1 TRAGO", "2 TRAGOS", "3 TRAGOS", "4 TRAGOS"};

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tragos);

        RecyclerView lalista = findViewById(R.id.elreciclerview);
        ElAdaptadorRecycler eladaptador = new ElAdaptadorRecycler(nombres, tragos);
        //Mostrar en textView
        TextView texto = findViewById(R.id.texto);
        texto.setText("");
        Bundle extras = getIntent().getExtras();
        String ganadorUsu = extras.getString("ganadorUsu");
        String perdedorUsu = extras.getString("perdedorUsu");
        String ganadorInv = extras.getString("ganadorInv");
        String perdedorInv = extras.getString("perdedorInv");
        eladaptador.setOnItemClickListener(new ElAdaptadorRecycler.OnItemClickListener() {
            @Override
            public void onItemClick(int position, int numTragos) {
                if (position == 0) {
                    numSeleccionTragos = 1;
                    if (ganadorUsu != null && perdedorInv != null) { //Si ha ganado el usuario, sumar tragos al invitado que ha perdido
                        String editado = perdedorInv + ", " + ganadorUsu + " ha ganado, bebes " + numSeleccionTragos + " trago!!!!";
                        texto.setText(editado); //En la etiqueta poner quien bebe y cuantos tragos
                    } else if (ganadorInv != null && perdedorUsu != null) { //Si ha ganado el invitado, sumar tragos al usuario que ha perdido
                        String editado = perdedorUsu + ", " + ganadorInv + " ha ganado, bebes " + numSeleccionTragos + " trago!!!!";
                        texto.setText(editado); //En la etiqueta poner quien bebe y cuantos tragos
                    }
                } else if (position == 1) {
                    numSeleccionTragos = 2;
                    if (ganadorUsu != null && perdedorInv != null) { //Si ha ganado el usuario, sumar tragos al invitado que ha perdido
                        String editado = perdedorInv + ", " + ganadorUsu + " ha ganado, bebes " + numSeleccionTragos + " tragos!!!!";
                        texto.setText(editado); //En la etiqueta poner quien bebe y cuantos tragos
                    } else if (ganadorInv != null && perdedorUsu != null) { //Si ha ganado el invitado, sumar tragos al usuario que ha perdido
                        String editado = perdedorUsu + ", " + ganadorInv + " ha ganado, bebes " + numSeleccionTragos + " tragos!!!!";
                        texto.setText(editado); //En la etiqueta poner quien bebe y cuantos tragos
                    }
                } else if (position == 2) {
                    numSeleccionTragos = 3;
                    if (ganadorUsu != null && perdedorInv != null) { //Si ha ganado el usuario, sumar tragos al invitado que ha perdido
                        String editado = perdedorInv + ", " + ganadorUsu + " ha ganado, bebes " + numSeleccionTragos + " tragos!!!!";
                        texto.setText(editado); //En la etiqueta poner quien bebe y cuantos tragos
                    } else if (ganadorInv != null && perdedorUsu != null) { //Si ha ganado el invitado, sumar tragos al usuario que ha perdido
                        String editado = perdedorUsu + ", " + ganadorInv + " ha ganado, bebes " + numSeleccionTragos + " tragos!!!!";
                        texto.setText(editado); //En la etiqueta poner quien bebe y cuantos tragos
                    }
                } else if (position == 3) {
                    numSeleccionTragos = 4;
                    if (ganadorUsu != null && perdedorInv != null) { //Si ha ganado el usuario, sumar tragos al invitado que ha perdido
                        String editado = perdedorInv + ", " + ganadorUsu + " ha ganado, bebes " + numSeleccionTragos + " tragos!!!!";
                        texto.setText(editado); //En la etiqueta poner quien bebe y cuantos tragos
                    } else if (ganadorInv != null && perdedorUsu != null) { //Si ha ganado el invitado, sumar tragos al usuario que ha perdido
                        String editado = perdedorUsu + ", " + ganadorInv + " ha ganado, bebes " + numSeleccionTragos + " tragos!!!!";
                        texto.setText(editado); //En la etiqueta poner quien bebe y cuantos tragos
                    }
                }
            }
        });
        lalista.setAdapter(eladaptador);
        GridLayoutManager elLayoutFinal = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        lalista.setLayoutManager(elLayoutFinal);

        //Iniciar otra partida
        Button btn_jugarDeNuevo = findViewById(R.id.jugarDeNuevo);
        btn_jugarDeNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tragos.this, InicioSesion.class);
                if (ContextCompat.checkSelfPermission(Tragos.this, Manifest.permission.POST_NOTIFICATIONS)!= PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Tragos.this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 11);
                }
                NotificationManager elManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                NotificationCompat.Builder elBuilder = new NotificationCompat.Builder(Tragos.this, "IdCanal");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel elCanal = new NotificationChannel("IdCanal", "NombreCanal", NotificationManager.IMPORTANCE_DEFAULT);
                    elManager.createNotificationChannel(elCanal);
                    elBuilder.setSmallIcon(android.R.drawable.stat_sys_warning)
                            .setContentTitle("Nueva partida")
                            .setContentText("Inicia sesión para una nueva partida.")
                            .setVibrate(new long[]{0, 1000, 500, 1000})
                            .setAutoCancel(true);

                    elCanal.setDescription("Descripción del canal");
                    elCanal.enableLights(true);
                    elCanal.setVibrationPattern(new long[]{0, 1000, 500, 1000});
                    elCanal.enableVibration(true);
                }
                elManager.notify(1, elBuilder.build());
                startActivity(intent);
            }
        });
    }
}