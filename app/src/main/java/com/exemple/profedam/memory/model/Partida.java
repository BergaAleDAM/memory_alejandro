package com.exemple.profedam.memory.model;


import com.exemple.profedam.memory.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Alejandro on 22/11/2016.
 */


public class Partida {

    private Integer[] totalCartes = {
            R.drawable.c0, R.drawable.c1,
            R.drawable.c2, R.drawable.c3,
            R.drawable.c4, R.drawable.c5,
            R.drawable.c6, R.drawable.c7,
            R.drawable.c8, R.drawable.c9,
            R.drawable.c10, R.drawable.c11
    };

    private ArrayList<Carta> llistaCartes;

    private int numeroCartes;


    /**
     * Aqui abajo estan los constructores de partida que podemos necesitar
     */
    public Partida() {
    }


    public Partida(Integer[] totalCartes, ArrayList<Carta> llistaCartes, int numeroCartes) {
        this.totalCartes = totalCartes;
        this.llistaCartes = llistaCartes;
        this.numeroCartes = numeroCartes;
    }


    public Partida(ArrayList<Carta> llistaCartes, int numeroCartes) {
        this.llistaCartes = llistaCartes;
        this.numeroCartes = numeroCartes;
    }


    public Partida(int numeroCartes) {
        this.numeroCartes = numeroCartes;
        List<Integer> llistaTotal = Arrays.asList(totalCartes);
        llistaCartes = new ArrayList();

        for (int i = 0; i < numeroCartes; i++) {
            llistaCartes.add(new Carta(llistaTotal.get(i / 2)));
        }

        Collections.shuffle(llistaCartes);

    }

    /**
     * el getter de listaCartes
     * @return
     */
    public ArrayList<Carta> getLlistaCartas() {
        return llistaCartes;
    }

    public int getNumeroCartes() {
        return numeroCartes;
    }

    /**
     * Con este metodo podemosir pasando por cada carta del arraylist de cartas y si esta front se añade a
     * las que estan front
     *
     * @return
     */
    public ArrayList<Carta> mostrarCartasFront() {
        ArrayList<Carta> listaCartasFront = new ArrayList();
        for (Carta carta : getLlistaCartas()) {
            if (carta.getEstat() == Carta.Estat.FRONT) {
                listaCartasFront.add(carta);
            }
        }
        return listaCartasFront;
    }

}