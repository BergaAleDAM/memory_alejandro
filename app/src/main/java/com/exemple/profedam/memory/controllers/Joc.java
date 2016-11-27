package com.exemple.profedam.memory.controllers;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;
import com.exemple.profedam.memory.R;
import com.exemple.profedam.memory.model.Partida;

public class Joc extends AppCompatActivity {

    private GridView gv;
    private Partida partida;
    private final GeneralListener listener = new GeneralListener(this);
    public static int contador;
    private int tiempoRestante = 60;

    public GridView getGv() {
        return gv;
    }

    public void setGv(GridView gv) {
        this.gv = gv;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    /**
     * Se llama a activity_joc
     * se recoge el numero de cartas y se cra una partida con ese numero de cartas
     * se establecen los listener y se inicializa un contador a 60 segundos
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joc);
        int cartes = getIntent().getIntExtra("diff", -33);
        gv = (GridView) findViewById(R.id.gridViewMemory);
        this.partida = new Partida(cartes);
        ImageAdapter adapter = new ImageAdapter(this, partida);

        gv.setAdapter(adapter);
        gv.setOnItemClickListener(listener);
        contador();
    }

    /**
     * se actualiza el tablero.
     */
    public void refrescarTablero() {
        ImageAdapter adapter = new ImageAdapter(this, partida);
        gv.setAdapter(adapter);
        gv.refreshDrawableState();
    }

    /**
     * Comprueba si el juego ha acabado bien
     * @return
     */
    public boolean comprobarFin() {
        boolean comprovar;
        if(contador == partida.getNumeroCartes()/2){
            comprovar = true;
        } else {
            comprovar =  false;
        }
        return comprovar;
    }


    /**
     * Contador que hace la cuenta atras para hacer el reto de girar las cartas.
     */
    public void contador() {

        final boolean acabar = true;

        new CountDownTimer(tiempoRestante * 1000, 1000) {

            public void onTick(long millisUntilFinished) {

                ((TextView) findViewById(R.id.textTimeLeft)).setText("Tiempo Restante: " + (millisUntilFinished / 1000));
            }
            public void onFinish() {

                ((TextView) findViewById(R.id.textTimeLeft)).setText("SE ACABO!");
                opcionFinale(acabar);
            }
        }.start();
    }

    /**
     * Metodo que acaba la partida y saca un alert dialog que pregunta si quieren volver a jugar
     */

    public void opcionFinale(boolean tiempoAcabado) {
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle((tiempoAcabado)?getString(R.string.tiempo):getString(R.string.ganador));
        ad.setMessage(R.string.tiempo2);
        ad.setCancelable(false);
        ad.setPositiveButton(R.string.menu, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                aceptar();
            }
        });
        ad.setNegativeButton(R.string.exit, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                finish();
            }
        });
        ad.show();
    }

    /**
     * metodo que te devuelve al menu del principio
     */
    public void aceptar() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }



}