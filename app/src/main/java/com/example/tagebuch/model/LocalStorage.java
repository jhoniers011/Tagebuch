package com.example.tagebuch.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.tagebuch.model.dao.CategoriaRoomDao;
import com.example.tagebuch.model.dao.PensamientoRoomDao;
import com.example.tagebuch.model.pojo.Categoria;
import com.example.tagebuch.model.pojo.Pensamiento;

@Database(entities = {Pensamiento.class, Categoria.class}, version = 1)
public abstract class LocalStorage extends RoomDatabase {

    private static LocalStorage localStorage;
    public abstract CategoriaRoomDao categoriaRoomDao();
    public abstract PensamientoRoomDao pensamientoRoomDao();
    //public abstract PensamientoRoomDao pensamientoRoomDao;

    public static LocalStorage getLocalStorage(final Context context){
        if (localStorage == null){

            localStorage = Room.databaseBuilder(context,
                    LocalStorage.class,
                    "Tagebuch-db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return localStorage;
    }

    public LocalStorage(){

    }

}
