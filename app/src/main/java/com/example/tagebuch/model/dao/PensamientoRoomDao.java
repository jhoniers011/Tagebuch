package com.example.tagebuch.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tagebuch.model.pojo.Pensamiento;

import java.util.List;

@Dao
public interface PensamientoRoomDao {

    @Query("SELECT * FROM pensamiento")
    List<Pensamiento> getAll();

    @Query("SELECT * FROM pensamiento  WHERE id = (SELECT MAX(ID) FROM pensamiento)")
    Pensamiento ListOne();


    @Insert
    void insertOne(Pensamiento pensamiento);


    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateOne(Pensamiento pensamiento);

    //Ejemplo para eliminar-actualizar varios.
    @Update
    void updateList(List<Pensamiento> pensamientos);

    @Query("UPDATE pensamiento SET titulo = :titulo, descripcion = :descripcion  WHERE id = :id")
    void updateCustom(String titulo,String descripcion,int id);


    @Query("DELETE FROM pensamiento")
    void deleteall();
    @Delete
    void deleteOne(Pensamiento pensamiento);


}
