package com.example.tagebuch.controller;

import java.util.Stack;

public class PilaTamanoFijo<Command> extends Stack<Command> {

    private int tamano;


    public PilaTamanoFijo(int tamano){
        super();
        this.tamano = tamano;
    }

    @Override
    public Command push(Command pensamiento){
        while (this.size() >= tamano) {
            this.remove(0);
        }
        return super.push(pensamiento);
    }
}
