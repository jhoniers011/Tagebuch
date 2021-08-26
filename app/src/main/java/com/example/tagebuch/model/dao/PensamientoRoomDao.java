package com.example.tagebuch.model.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tagebuch.model.pojo.Pensamiento;

import java.util.List;

@Dao
public interface PensamientoRoomDao {

    @Query("SELECT * FROM pensamiento")
    List<Pensamiento> getAll();

    @Insert
    void insertOne(Pensamiento pensamiento);

    @Update
    void updateOne(Pensamiento pensamiento);

    //Ejemplo para eliminar-actualizar varios.
    @Update
    void updateList(List<Pensamiento> pensamientos);


}
