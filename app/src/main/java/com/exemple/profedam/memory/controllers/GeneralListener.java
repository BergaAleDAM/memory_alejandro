package com.exemple.profedam.memory.controllers;

import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import com.exemple.profedam.memory.model.Carta;
import com.exemple.profedam.memory.model.Partida;

import java.util.ArrayList;

public class GeneralListener implements AdapterView.OnItemClickListener, Runnable {

    private Joc tauler;
    private Carta carta;
    private boolean unamas = true;
    private ArrayList<Carta> listaCartasFront;

    public GeneralListener(Joc tauler) {
        this.tauler = tauler;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        Partida partida = tauler.getPartida();

        if (unamas) {
            carta = tauler.getPartida().getLlistaCartas().get(position);
            carta.girar();
            tauler.refrescarTablero();
            listaCartasFront = partida.mostrarCartasFront();


            if (listaCartasFront.size() == 2 && listaCartasFront.get(0).getFrontImage() != listaCartasFront.get(1).getFrontImage()) {
                Handler delay = new Handler();
                delay.postDelayed(this, 1000);
                this.unamas = false;



            } else if (listaCartasFront.size() == 2) {
                listaCartasFront.get(0).setEstat(Carta.Estat.FIXED);
                listaCartasFront.get(1).setEstat(Carta.Estat.FIXED);
                Joc.contador++;
            }
        }

        if(tauler.comprobarFin()){
            tauler.opcionFinale(false);
            Joc.contador = 0;
        }

    }


    @Override
    public void run() {
        listaCartasFront.get(0).girar();
        listaCartasFront.get(1).girar();
        tauler.refrescarTablero();
        unamas = true;
    }
}
