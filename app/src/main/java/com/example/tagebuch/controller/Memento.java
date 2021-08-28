package com.example.tagebuch.controller;

import com.example.tagebuch.model.pojo.Pensamiento;

public class Memento {

    private Pensamiento pensamiento;
    private String Operacion;
    private String OperacionInversa;

    public Memento(Pensamiento pensamiento, String Operacion, String OperacionInversa){

        this. pensamiento = pensamiento;
        this.Operacion = Operacion;
        this.OperacionInversa = OperacionInversa;

    }



}
