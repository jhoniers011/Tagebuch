package com.example.tagebuch.controller;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.example.tagebuch.Adapter.CategoriasAdapter;
import com.example.tagebuch.model.LocalStorage;
import com.example.tagebuch.model.dao.CategoriaRoomDao;
import com.example.tagebuch.model.dao.PensamientoRoomDao;
import com.example.tagebuch.model.pojo.Categoria;
import com.example.tagebuch.model.pojo.Pensamiento;
import com.example.tagebuch.view.fragments.ReportarPensamientoFragment;

import java.util.List;

public class MainActivityController {

    private CategoriaRoomDao categoriaRoomDao;
    private PensamientoRoomDao pensamientoRoomDao;

    public List<Categoria> obtenerCategorias(Activity activity){

        this.categoriaRoomDao = LocalStorage.getLocalStorage(activity.getApplicationContext()).categoriaRoomDao();
        return this.categoriaRoomDao.getAll();

    }

    public void comprobarPensamiento(ReportarPensamientoFragment fragment, String titulo, String descripcion,CategoriasAdapter adapter){

        if (titulo == null || titulo.compareTo("") == 0){
            fragment.tituloVacio();
            return;
        }

        if (titulo.length() > 100){
            fragment.limitetitulo();
            return;
        }

        if (descripcion == null || descripcion.compareTo("") == 0){
            fragment.descripcionVacio();
            return;
        }

        if (adapter.getPosition() == -1){
            fragment.categoriaNoSeleccionada();
            return;
        }

        //si esta correcto
        //metodo para mostrar que está correcto
    }


    public void guardarPensamiento(Activity activity, String titulo, String descripcion, Categoria categoria){

        Pensamiento nuevopensamiento = new Pensamiento();
        nuevopensamiento.setTitulo(titulo);
        nuevopensamiento.setDescripcion(descripcion);
        nuevopensamiento.setCategoria(categoria);

        this.pensamientoRoomDao = LocalStorage.getLocalStorage(activity.getApplicationContext()).pensamientoRoomDao();
        this.pensamientoRoomDao.insertOne(nuevopensamiento);

    }
}
