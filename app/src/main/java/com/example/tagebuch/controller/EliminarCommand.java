package com.example.tagebuch.controller;

import android.content.Context;

import com.example.tagebuch.model.LocalStorage;
import com.example.tagebuch.model.dao.PensamientoRoomDao;
import com.example.tagebuch.model.pojo.Pensamiento;

public class EliminarCommand extends Command{

    private PensamientoRoomDao pensamientoRoomDao;
    private Context context;
    private Pensamiento pensamiento;

    public EliminarCommand(Context context, Pensamiento pensamiento) {
        this.context = context;
        this.pensamiento = pensamiento;
    }

    @Override
    public void execute() {

        this.pensamientoRoomDao = LocalStorage.getLocalStorage(context).pensamientoRoomDao();
        this.pensamientoRoomDao.deleteOne(pensamiento);


    }

    @Override
    public void unexecute() {

        this.pensamientoRoomDao = LocalStorage.getLocalStorage(context).pensamientoRoomDao();
        this.pensamientoRoomDao.insertOne(pensamiento);


    }
}
