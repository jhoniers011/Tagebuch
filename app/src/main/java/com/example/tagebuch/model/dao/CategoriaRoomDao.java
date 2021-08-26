package com.example.tagebuch.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tagebuch.model.pojo.Categoria;
import com.example.tagebuch.model.pojo.Pensamiento;

import java.util.List;

@Dao
public interface CategoriaRoomDao {

    @Query("SELECT * FROM categoria")
    List<Categoria> getAll();

    @Insert
    void insertOne(Categoria categoria);

    @Update
    void updateOne(Categoria categoria);

    //Ejemplo para eliminar-actualizar varios.
    @Update
    void updateList(List<Categoria> categorias);

    @Delete
    void deleteList(List<Categoria> categorias);

}
