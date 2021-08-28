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
            return;
        }
        if (comprobarLimiteTitulo(pensamiento.getTitulo())) {
            fragment.limitetitulo();
            return;
        }

        if (comprobarTextoVacio(pensamiento.getDescripcion())) {

            fragment.descripcionVacio();
            return;
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

    //Cargar las categorias si no están en la base de datos.
    public void cargarCategorias(Context context){
        this.categoriaRoomDao = LocalStorage.getLocalStorage(context).categoriaRoomDao();
        List<Categoria> categorias = this.categoriaRoomDao.getAll();

        if(categorias.size() == 0){

        Categoria categoria1 = new Categoria();
        categoria1.setCtitulo("Pensamiento deductivo ");
        categoria1.setCdescripcion("parte de afirmaciones basadas en ideas abstractas y universales para aplicarlas a casos particulares.");
        categoria1.setColor("#fa0000");
        categoria1.setCid(7);

        Categoria categoria2 = new Categoria();
        categoria2.setCtitulo("Pensamiento inductivo");
        categoria2.setCdescripcion("No parte de afirmaciones generales, sino que se basa en casos particulares y, a partir de ellos, genera ideas generales.");
        categoria2.setColor("#1900fa");

        Categoria categoria3 = new Categoria();
        categoria3.setCtitulo("Pensamiento analítico");
        categoria3.setCdescripcion("Crea piezas de información a partir de una unidad informacional amplia y llega a conclusiones viendo el modo en el que interactúan entre sí estos “fragmentos”.");
        categoria3.setColor("#fae900");

        Categoria categoria4 = new Categoria();
        categoria4.setCtitulo("Pensamiento creativo");
        categoria4.setCdescripcion("Se juega a crear soluciones originales y únicas ante problemas, mediante el cuestionamiento de las normas que en un principio parecen ser evidentes.");
        categoria4.setColor("#00eefa");

        Categoria categoria5 = new Categoria();
        categoria5.setCtitulo("Pensamiento divergente");
        categoria5.setCdescripcion("Se establece una división entre dos o más aspectos de una idea, y se explora las posibilidades de mantener esta “partición”.");
        categoria5.setColor("#15fa00");

        Categoria categoria6 = new Categoria();
        categoria6.setCtitulo("Pensamiento convergente");
        categoria6.setCdescripcion("Se da un proceso por el cual nos damos cuenta de que hay diferentes hechos o realidades que encajan entre sí a pesar de que en un principio parecía que no tenían nada en común.");
        categoria6.setColor("#b300fa");

        this.categoriaRoomDao.insertOne(categoria1);
        this.categoriaRoomDao.insertOne(categoria2);
        this.categoriaRoomDao.insertOne(categoria3);
        this.categoriaRoomDao.insertOne(categoria4);
        this.categoriaRoomDao.insertOne(categoria5);
        this.categoriaRoomDao.insertOne(categoria6);

        }
        return;

    }

    public void crearMemento(){

        //return new Memento()

    }

    public void restaurarMemento(){
        //deshacer memento
    }


}
