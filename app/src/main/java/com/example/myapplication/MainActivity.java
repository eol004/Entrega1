package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private View pantallaJ1;
    private View pantallaJ2;
    private ImageView caballoJ1;
    private ImageView caballoJ2;
    private View divisor;
    private Button boton_j1;
    private Button boton_j2;
    private Button boton_pausa;
    private int posJ1 = 0;
    private int posJ2=0;
    private boolean ganaJ1;
    private boolean ganaJ2;
    private boolean empezar;
    private String usuarioSesion;
    private TextView nom_j1;
    private TextView nom_j2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Jugadores
        pantallaJ1 = findViewById(R.id.pantalla_j1);
        pantallaJ2 = findViewById(R.id.pantalla_j2);
        //Caballos
        caballoJ1 = findViewById(R.id.caballoJ1);
        caballoJ2 = findViewById(R.id.caballoJ2);
        //divisor
        divisor = findViewById(R.id.divisor);
        //Botones
        boton_j1 = findViewById(R.id.boton_j1);
        boton_j2 = findViewById(R.id.boton_j2);
        boton_pausa = findViewById(R.id.btn_pausa);
        //TextView
        nom_j1 = findViewById(R.id.nom_j1);
        nom_j2 = findViewById(R.id.nom_j2);
        //Nombre del usuario que ha iniciado sesion
        Bundle extra = getIntent().getExtras();
        if (extra != null){
            usuarioSesion = extra.getString("usuario");
            Log.d("LLEGADO", "Soy el usuario --> " + usuarioSesion);
        }

        //Dialogo para empezar el juego
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Jugamos ya?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                jugar();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish(); //Cerrar la actividad si el usuario elige "No"
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void cuentaAtras(){
        //Cuenta atras para empezar juego (https://stackoverflow.com/questions/8857590/android-countdowntimer-skips-last-ontick)
        empezar = false;
        new CountDownTimer(10000, 1000) {
            public void onTick(long millisUntilFinished) {
                // Modificar el texto tiempo que queda
                nom_j1.setText("El juego comienza en: " + millisUntilFinished / 1000);
                nom_j2.setText("El juego comienza en: " + millisUntilFinished / 1000);
                // Deshabilitar los botones hasta que termine la cuenta atras (https://es.stackoverflow.com/questions/23263/inhabilitar-un-boton)
                boton_j1.setEnabled(false);
                boton_j2.setEnabled(false);
            }
            public void onFinish() {
                // Quitar la cuenta atras
                Bundle extras = getIntent().getExtras();
                nom_j1.setText(extras.getString("usuario"));
                nom_j2.setText(extras.getString("invitado"));
                // Habilitar los botones
                boton_j1.setEnabled(true);
                boton_j2.setEnabled(true);
                empezar = true; //Chivato para saber cuando pueden dar al boton
            }
        }.start();
    }

    public void moverCaballoJ1(){
        boton_j1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (empezar) {
                    if (ganaJ2) {
                        return;
                    }
                    //Obtener la posicion del caballo
                    int[] posC = new int[2]; //Array para posiciones X e Y
                    caballoJ1.getLocationOnScreen(posC); //se guarda en posC la posicion actual del caballo
                    //Obtener la posicion del divisor
                    int[] posDiv = new int[2];
                    divisor.getLocationOnScreen(posDiv);
                    posJ1 = posJ1 + 10; // mover hacia abajo
                    //delimitar para que no vaya más alla del divisor
                    if (posC[1] <= posDiv[1] && posC[1] - caballoJ1.getHeight() <= posDiv[1] && !ganaJ2) {
                        caballoJ1.setY(posJ1);
                    } else {
                        ganaJ1 = true;
                        ganaJ2 = false;
                        nom_j1.setText("¡Ganaste!\n¡A repartir tragos!");
                        nom_j2.setText("¡Perdiste!\n¡A recibir tragos!");

                        //Dialogo
                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                        builder.setTitle("Fin del juego");
                    }
                }
            }
        });
    }

    public void moverCaballoJ2(){
        boton_j2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (empezar) {
                    if (ganaJ1) {
                        return;
                    }
                    int[] posC = new int[2];
                    caballoJ2.getLocationOnScreen(posC);

                    int[] posDiv = new int[2];
                    divisor.getLocationOnScreen(posDiv);
                    posJ2 = posJ2 - 10;

                    if (posC[1] >= posDiv[1] && posC[1] + caballoJ1.getHeight() >= posDiv[1] && !ganaJ1) {
                        caballoJ2.setY(caballoJ2.getTop() + posJ2);
                    } else {
                        ganaJ2 = true;
                        ganaJ1 = false;

                        nom_j2.setText("¡Ganaste!\n¡A repartir tragos!");
                        nom_j1.setText("¡Perdiste!\n¡A recibir tragos!");
                    }
                }
            }
        });
    }

    public void jugar(){
        cuentaAtras();
        moverCaballoJ1();
        moverCaballoJ2();
    }

    public void pausar(){
        boton_pausa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Dialogo de pausa
                AlertDialog.Builder builderP = new AlertDialog.Builder(getApplicationContext());
                builderP.setMessage("Juego pausado. ¿Quieres reanudar el juego?");
                builderP.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        jugar();
                    }
                });
                builderP.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish(); //Cerrar la actividad si el usuario elige "No"
                    }
                });
                AlertDialog dialog = builderP.create();
                dialog.show();
            }
        });
    }
}