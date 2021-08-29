package com.example.tagebuch.controller;

import android.content.Context;

import com.example.tagebuch.model.LocalStorage;
import com.example.tagebuch.model.dao.PensamientoRoomDao;
import com.example.tagebuch.model.pojo.Pensamiento;

public class EditarCommand extends Command{

    private PensamientoRoomDao pensamientoRoomDao;
    private Context context;
    private Pensamiento pensamientoeditado;
    private Pensamiento pensamientosineditar;


    public EditarCommand( Context context, Pensamiento pensamientoeditado,Pensamiento pensamientosineditar) {
        this.context = context;
        this.pensamientoeditado = pensamientoeditado;
        this.pensamientosineditar = pensamientosineditar;
    }

    @Override
    public void execute() {

        this.pensamientoRoomDao = LocalStorage.getLocalStorage(context).pensamientoRoomDao();
        this.pensamientoRoomDao.updateOne(pensamientoeditado);

    }

    @Override
    public void unexecute() {

        this.pensamientoRoomDao = LocalStorage.getLocalStorage(context).pensamientoRoomDao();
        this.pensamientoRoomDao.updateOne(pensamientosineditar);

    }
}
