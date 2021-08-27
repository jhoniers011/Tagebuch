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
import com.example.tagebuch.view.fragments.EditarPensamientoFragment;
import com.example.tagebuch.view.fragments.EliminarPensamientoFragment;
import com.example.tagebuch.view.fragments.ReportarPensamientoFragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivityController {

    private CategoriaRoomDao categoriaRoomDao;
    private PensamientoRoomDao pensamientoRoomDao;

    public List<Categoria> obtenerCategorias(Activity activity){

        this.categoriaRoomDao = LocalStorage.getLocalStorage(activity.getApplicationContext()).categoriaRoomDao();
        return this.categoriaRoomDao.getAll();

    }

    public void comprobarPensamiento(ReportarPensamientoFragment fragment, String titulo, String descripcion,CategoriasAdapter adapter){


        if(comprobarTextoVacio(titulo)){
            fragment.tituloVacio();
            return;
        }

        if (comprobarLimiteTitulo(titulo)){
            fragment.limitetitulo();
            return;
        }

        if (comprobarTextoVacio(descripcion)){
            fragment.descripcionVacio();
            return;
        }


        if (adapter.getPosition() == -1){
            fragment.categoriaNoSeleccionada();
            return;
        }

        //si esta correcto
        //metodo para mostrar que está correcto
        guardarPensamiento(fragment.getActivity(),titulo,descripcion,adapter.getSelected());
        fragment.mostrarpensamientoCorrecto();
    }

    public Boolean  comprobarTextoVacio(String texto) {

        if (texto == null || texto.compareTo("") == 0){
            return true;
        }
        return false;

    }

    public Boolean  comprobarLimiteTitulo(String titulo) {

        if (titulo.length() > 100){
            return true;
        }
        return false;

    }

    public void comprobarEditarPensamiento(EditarPensamientoFragment fragment,Pensamiento pensamiento) {

        if (comprobarTextoVacio(pensamiento.getTitulo())) {
            fragment.tituloVacio();
        }
        if (comprobarLimiteTitulo(pensamiento.getTitulo())) {
            fragment.limitetitulo();
        }

        if (comprobarTextoVacio(pensamiento.getDescripcion())) {

            fragment.descripcionVacio();
        }

        //Si es correcto

        actualizarPensamiento(fragment.getActivity(),pensamiento);
        fragment.EditarPensamientoCorrecto();


    }



    public void guardarPensamiento(Activity activity, String titulo, String descripcion, Categoria categoria){

        Pensamiento nuevopensamiento = new Pensamiento();
        nuevopensamiento.setTitulo(titulo);
        nuevopensamiento.setDescripcion(descripcion);
        nuevopensamiento.setCategoria(categoria);
        nuevopensamiento.setFecha(new SimpleDateFormat("dd-MM-yyyy").format(new Date()) );

        this.pensamientoRoomDao = LocalStorage.getLocalStorage(activity.getApplicationContext()).pensamientoRoomDao();
        this.pensamientoRoomDao.insertOne(nuevopensamiento);

    }

    public void actualizarPensamiento(Activity activity,Pensamiento pensamiento){
        this.pensamientoRoomDao = LocalStorage.getLocalStorage(activity.getApplicationContext()).pensamientoRoomDao();
        //this.pensamientoRoomDao.updateOne(pensamiento);

        this.pensamientoRoomDao.updateCustom(pensamiento.getTitulo(),pensamiento.getDescripcion(),pensamiento.getId());


    }

    public void eliminarPensamiento(EliminarPensamientoFragment fragment , Pensamiento pensamiento){
        this.pensamientoRoomDao = LocalStorage.getLocalStorage(fragment.getActivity().getApplicationContext()).pensamientoRoomDao();
        this.pensamientoRoomDao.deleteOne(pensamiento);

        fragment.eliminarPensamientoCorrecto();

    }

    public List<Pensamiento> listarPensamiento(Activity activity){
        this.pensamientoRoomDao = LocalStorage.getLocalStorage(activity.getApplicationContext()).pensamientoRoomDao();
        return this.pensamientoRoomDao.getAll();
    }
}
