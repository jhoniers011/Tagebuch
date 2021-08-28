package com.example.tagebuch.controller;

import java.util.Stack;

public class PilaTamanoFijo<Categoria> extends Stack<Categoria> {

    private int tamano;


    public PilaTamanoFijo(int tamano){
        super();
        this.tamano = tamano;
    }

    @Override
    public Categoria push(Categoria categoria){
        while (this.size() >= tamano) {
            this.remove(0);
        }
        return super.push(categoria);
    }
}
